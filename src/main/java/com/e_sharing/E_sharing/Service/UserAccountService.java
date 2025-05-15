package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
    //Injection della repository
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository UserAccountRepository) {
        this.userAccountRepository = UserAccountRepository;
    }

    //Metodo per ottenere tutti gli utenti
    public List<UserAccount> getAllUserAccounts() {
        return (List<UserAccount>) userAccountRepository.findAll();
    }

    //Metodo per ottenere un utente specifico
    public Optional<UserAccount> findUserByEmail(String email) {
        return userAccountRepository.findById(email);
    }

    //Salva un utente controllando che non sia gia presente
    public UserAccount saveUserAccount(UserAccount user) {
        if(userAccountRepository.existsById(user.getEmail())) {
            throw new RuntimeException("Utente gia presente con email: " + user.getEmail());
        }
        return userAccountRepository.save(user);
    }

    //Elimina un utente
    public void deleteUserAccount(String email) {
        userAccountRepository.deleteById(email);
    }


    public UserAccount updateUserInfo(String email, UserAccount user) {
        return userAccountRepository.findById(email)
                .map(UTENTE -> {
                    UTENTE.setNome(user.getNome());
                    UTENTE.setCognome(user.getCognome());
                    return userAccountRepository.save(UTENTE);
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


}
