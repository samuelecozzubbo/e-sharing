package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Repositories.LeadRepository;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
    //Injection della repository
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;
    private final LeadRepository leadRepository;
    private final UsernameUpdateService usernameUpdateService;

    @Autowired
    public UserAccountService(UserAccountRepository UserAccountRepository, ModelMapper modelMapper, GenericUtils genericUtils, LeadRepository leadRepository, UsernameUpdateService usernameUpdateService) {
        this.userAccountRepository = UserAccountRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
        this.leadRepository = leadRepository;
        this.usernameUpdateService = usernameUpdateService;
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
        if (userAccountRepository.existsById(nuovaEmail)) {
            return "La nuova email è già in uso.";
        }

        Optional<UserAccount> utenteOpt = userAccountRepository.findById(vecchiaEmail);
        if (utenteOpt.isEmpty()) {
            return "Utente non trovato.";
        }

        UserAccount utenteVecchio = utenteOpt.get();
        List<Lead> vecchiLead = new ArrayList<>(utenteVecchio.getLeads());

        // Disattivo lo username per evitare vincoli unique
        usernameUpdateService.disattivaUsername(vecchiaEmail, "deleted_" + utenteVecchio.getUsername());

        // Elimino l'utente → eliminerà anche i lead in cascade
        userAccountRepository.deleteById(vecchiaEmail);

        // Creo il nuovo utente con la nuova email
        UserAccount nuovoUtente = new UserAccount(
                nuovaEmail,
                utenteVecchio.getNome(),
                utenteVecchio.getCognome(),
                utenteVecchio.getUsername(),
                utenteVecchio.getPassword()
        );
        UserAccount nuovoUtenteSalvato = userAccountRepository.save(nuovoUtente);

        // Ricreo i lead e li assegno al nuovo utente
        for (Lead vecchioLead : vecchiLead) {
            Lead nuovoLead = new Lead(
                    vecchioLead.getDataAcquisto(),
                    vecchioLead.getStatus(),
                    vecchioLead.getSconto(),
                    nuovoUtenteSalvato
            );
            leadRepository.save(nuovoLead);
        }

        return "Email aggiornata e lead ricreati con successo!";
    }


    @Transactional
    public void disattivaUsername(String email, String nuovoUsername) {
        userAccountRepository.findById(email).ifPresent(user -> {
            user.setUsername(nuovoUsername);
            userAccountRepository.save(user);
        });
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
