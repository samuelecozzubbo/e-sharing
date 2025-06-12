package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.StoricoNoleggioDTO;
import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.DTO.VehicleDTO;
import com.e_sharing.E_sharing.Enum.LeadState;
import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Model.LeadVehicle;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Repositories.LeadRepository;
import com.e_sharing.E_sharing.Repositories.LeadVehicleRepository;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service per la gestione delle operazioni sugli account utente.
 */
@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;
    private final LeadRepository leadRepository;
    private final UsernameUpdateService usernameUpdateService;
    private final LeadVehicleRepository leadVehicleRepository;

    @Autowired
    public UserAccountService(
            UserAccountRepository userAccountRepository,
            ModelMapper modelMapper,
            GenericUtils genericUtils,
            LeadRepository leadRepository,
            UsernameUpdateService usernameUpdateService,
            LeadVehicleRepository leadVehicleRepository
    ) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
        this.leadRepository = leadRepository;
        this.usernameUpdateService = usernameUpdateService;
        this.leadVehicleRepository = leadVehicleRepository;
    }

    /**
     * Ottiene la lista di tutti gli utenti (UserAccountDTO).
     */
    public List<UserAccountDTO> getAllUserAccountsAuto() {
        Iterable<UserAccount> utenti = userAccountRepository.findAll();
        return genericUtils.iterableToListAndDTO(utenti, UserAccountDTO.class);
    }

    /**
     * Ricerca un utente tramite email e lo restituisce come DTO.
     */
    public Optional<UserAccountDTO> findUserByEmail(String email) {
        return userAccountRepository.findById(email)
                .map(utente -> modelMapper.map(utente, UserAccountDTO.class));
    }

    /**
     * Salva un nuovo utente controllando che non sia già presente.
     */
    public UserAccount saveUserAccount(UserAccountDTO user) {
        if (userAccountRepository.existsById(user.getEmail())) {
            throw new RuntimeException("Utente gia presente con email: " + user.getEmail());
        }
        return userAccountRepository.save(modelMapper.map(user, UserAccount.class));
    }

    /**
     * Elimina un utente tramite email.
     */
    public void deleteUserAccount(String email) {
        userAccountRepository.deleteById(email);
    }

    /**
     * Aggiorna nome e cognome di un utente.
     */
    public UserAccountDTO updateUserInfo(String email, UserAccountDTO userDTO) {
        return userAccountRepository.findById(email)
                .map(user -> {
                    user.setNome(userDTO.getNome());
                    user.setCognome(userDTO.getCognome());
                    return modelMapper.map(userAccountRepository.save(user), UserAccountDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + email));
    }

    /**
     * Transazione per modificare l'email di un utente e ricreare i lead associati.
     */
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

        usernameUpdateService.disattivaUsername(vecchiaEmail, "deleted_" + utenteVecchio.getUsername());

        userAccountRepository.deleteById(vecchiaEmail);

        UserAccount nuovoUtente = new UserAccount(
                nuovaEmail,
                utenteVecchio.getNome(),
                utenteVecchio.getCognome(),
                utenteVecchio.getUsername(),
                utenteVecchio.getPassword()
        );
        UserAccount nuovoUtenteSalvato = userAccountRepository.save(nuovoUtente);

        // Ricrea i lead associati al nuovo utente
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

    /**
     * Aggiorna l'username di un utente.
     */
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

    /**
     * Aggiorna la password di un utente.
     */
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

    /**
     * Elimina tutti gli utenti.
     */
    public void deleteAllUsers() {
        userAccountRepository.deleteAll();
    }

    /**
     * Restituisce lo storico dei noleggi per un utente.
     */
    public List<StoricoNoleggioDTO> getStoricoNoleggiUtente(String email) {
        List<LeadVehicle> leadVehicles = leadVehicleRepository.findAllByLead_UserAccount_Email(email);

        return leadVehicles.stream()
                .map(lv -> {
                    var v = lv.getVehicle();
                    VehicleDTO vehicleDTO = new VehicleDTO(
                            v.getId(),
                            v.getVehicleType(),
                            v.getCostoNoleggio(),
                            v.getLivelloBatteria(),
                            v.getState(),
                            v.getSite() != null ? v.getSite().getId() : null
                    );
                    LeadState status = lv.getLead().getStatus();
                    return new StoricoNoleggioDTO(
                            vehicleDTO,
                            lv.getDataNoleggio(),
                            lv.getDurataNoleggioGiorni(),
                            lv.getTotale(),
                            status
                    );
                })
                .collect(Collectors.toList());
    }
}