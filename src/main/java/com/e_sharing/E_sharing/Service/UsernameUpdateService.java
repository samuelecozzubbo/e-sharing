package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsernameUpdateService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UsernameUpdateService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional
    public void disattivaUsername(String email, String nuovoUsername) {
        userAccountRepository.findById(email).ifPresent(user -> {
            user.setUsername(nuovoUsername);
            userAccountRepository.save(user);
        });
    }
}
