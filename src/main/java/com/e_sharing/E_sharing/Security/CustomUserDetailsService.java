package com.e_sharing.E_sharing.Security;

import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Implementazione di UserDetailsService per l'integrazione tra Spring Security e il dominio UserAccount.
 * <p>
 * Carica i dettagli dell'utente dal database in base all'email, per consentire l'autenticazione.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    /**
     * Costruttore con iniezione del repository utenti.
     * @param userAccountRepository repository per l'accesso agli UserAccount
     */
    @Autowired
    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Carica i dettagli di un utente dato l'username (in questo caso, l'email).
     * @param email email dell'utente da autenticare
     * @return UserDetails per Spring Security
     * @throws UsernameNotFoundException se l'utente non viene trovato
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + email));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER") // puoi personalizzare i ruoli
                .build();
    }
}