package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAccountService {
    //Injection della repository
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;

    @Autowired
    public UserAccountService(UserAccountRepository UserAccountRepository, ModelMapper modelMapper, GenericUtils genericUtils) {
        this.userAccountRepository = UserAccountRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
    }

    //Metodo per ottenere tutti gli utenti
    /*public List<UserAccountDTO> getAllUserAccounts() {
        List<UserAccount> utenti = GenericUtils.iterableToList(userAccountRepository.findAll());
        return utenti.stream()
                .map(utente -> modelMapper.map(utente, UserAccountDTO.class))
                .toList();
    }*/

    //Metodo per ottenere tutti gli utenti 2.0 con conversione
    public List<UserAccountDTO> getAllUserAccountsAuto() {
        Iterable<UserAccount> utenti = userAccountRepository.findAll();
        // Uso il metodo d'istanza tramite l'istanza iniettata
        return genericUtils.iterableToListAndDTO(utenti, UserAccountDTO.class);
    }

    //Metodo per ottenere un utente specifico
    public Optional<UserAccountDTO> findUserByEmail(String email) {
        return userAccountRepository.findById(email)
                .map(utente -> modelMapper.map(utente, UserAccountDTO.class));
    }


    //Salva un utente controllando che non sia gia presente
    public UserAccount saveUserAccount(UserAccountDTO user) {
        if(userAccountRepository.existsById(user.getEmail())) {
            throw new RuntimeException("Utente gia presente con email: " + user.getEmail());
        }
        return userAccountRepository.save(modelMapper.map(user, UserAccount.class));
    }

    //Elimina un utente
    public void deleteUserAccount(String email) {
        userAccountRepository.deleteById(email);
    }


    // ✅ Aggiorna nome/cognome utente
    public UserAccountDTO updateUserInfo(String email, UserAccountDTO userDTO) {
        return userAccountRepository.findById(email)
                .map(user -> {
                    user.setNome(userDTO.getNome());
                    user.setCognome(userDTO.getCognome());
                    return modelMapper.map(userAccountRepository.save(user), UserAccountDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + email));
    }

    //Transazioni per modificare le informazioni di un utente
    @Transactional
    public String aggiornaEmailUtente(String vecchiaEmail, String nuovaEmail) {
        Optional<UserAccount> utenteEsistente = userAccountRepository.findById(vecchiaEmail);
        if (utenteEsistente.isPresent()) {
            UserAccount utente = utenteEsistente.get();
            // 1. Verifica se la nuova email è già in uso
            if (userAccountRepository.existsById(nuovaEmail)) {
                return "La nuova email è già in uso.";
            }
            // 2. Aggiorna l'email dell'utente
            utente.setEmail(nuovaEmail);
            userAccountRepository.save(utente);
            // 3. Se ci fossero altre tabelle con l'email come chiave esterna,
            // dovresti aggiornare anche quelle qui dentro la stessa transazione.
            // Ad esempio, se 'Lead' avesse 'Utente_email' come FK:
            // leadRepository.updateUtenteEmail(vecchiaEmail, nuovaEmail);
            return "Email aggiornata con successo.";
        } else {
            return "Utente con la vecchia email non trovato.";
        }
    }

    @Transactional
    public String aggiornaUsernameUtente(String email, String nuovoUsername) {
        Optional<UserAccount> utenteEsistente = userAccountRepository.findById(email);
        if (utenteEsistente.isPresent()) {
            UserAccount utente = utenteEsistente.get();
            utente.setUsername(nuovoUsername);
            userAccountRepository.save(utente);
            return "Username aggiornato con successo.";
        } else {
            return "Utente non trovato.";
        }
    }

    @Transactional
    public String aggiornaUsernameUtenteStream(String email, String nuovoUsername) {
        return userAccountRepository.findById(email)
                .map(user -> {
                    user.setUsername(nuovoUsername);
                    userAccountRepository.save(user);
                    return "Username aggiornato con successo.";
                })
                .orElse("Utente non trovato.");
    }

    @Transactional
    public String aggiornaPasswordUtente(String email, String nuovaPassword) {
        Optional<UserAccount> utenteEsistente = userAccountRepository.findById(email);
        if (utenteEsistente.isPresent()) {
            UserAccount utente = utenteEsistente.get();
            utente.setPassword(nuovaPassword);
            userAccountRepository.save(utente);
            return "Password aggiornata con successo.";
        } else {
            return "Utente non trovato.";
        }
    }

    //LOGIN
    public boolean login(String email, String password) {
        return userAccountRepository.findById(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    //Cancella tutti gli utenti
    public void deleteAllUsers() {
        userAccountRepository.deleteAll();
    }




}
