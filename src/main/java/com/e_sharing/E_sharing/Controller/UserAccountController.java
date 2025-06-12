package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.StoricoNoleggioDTO;
import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Service.UserAccountService;
import com.e_sharing.E_sharing.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione delle operazioni relative agli account utente.
 * <p>
 * Espone endpoint per la registrazione, autenticazione, aggiornamento,
 * eliminazione degli utenti e gestione dello storico noleggi.
 * Utilizza il servizio {@link UserAccountService} per la logica di business.
 * Tutte le risposte sono in formato {@link UserAccountDTO}, {@link UserAccount} o {@link StoricoNoleggioDTO}.
 * </p>
 *
 * <ul>
 *   <li>{@code GET /user} - Restituisce la lista di tutti gli utenti</li>
 *   <li>{@code GET /user/find/{email}} - Cerca un utente tramite email</li>
 *   <li>{@code POST /user/register} - Registra un nuovo utente</li>
 *   <li>{@code DELETE /user/delete/email/{email}} - Elimina un utente tramite email</li>
 *   <li>{@code DELETE /user/delete/all} - Elimina tutti gli utenti</li>
 *   <li>{@code PUT /user/update/nomecognome/{email}} - Aggiorna nome e cognome dell’utente</li>
 *   <li>{@code PUT /user/update/email/{email}} - Aggiorna l’email dell’utente</li>
 *   <li>{@code PUT /user/update/username/{email}} - Aggiorna lo username dell’utente</li>
 *   <li>{@code PUT /user/update/password/{email}} - Aggiorna la password dell’utente</li>
 *   <li>{@code GET /user/storiconoleggi/{email}} - Restituisce lo storico noleggi dell’utente</li>
 * </ul>
 */
@RestController
@RequestMapping("/user")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    /**
     * Restituisce la lista di tutti gli utenti presenti nel sistema.
     *
     * @return lista di {@link UserAccountDTO}
     */
    @GetMapping
    public List<UserAccountDTO> getAllUsers(){
        return userAccountService.getAllUserAccountsAuto();
    }

    /**
     * Cerca un utente tramite il suo indirizzo email.
     *
     * @param email indirizzo email dell’utente
     * @return un {@link Optional} contenente il {@link UserAccountDTO}, se trovato
     */
    @GetMapping("/find/{email}")
    public Optional<UserAccountDTO> findByEmail(@PathVariable String email) {
        return userAccountService.findUserByEmail(email);
    }

    /**
     * Registra un nuovo utente nel sistema.
     *
     * @param user oggetto {@link UserAccountDTO} da registrare
     * @return l’oggetto {@link UserAccount} appena creato
     */
    @PostMapping("/register")
    public UserAccount inserisciUtente(@RequestBody UserAccountDTO user){
        return userAccountService.saveUserAccount(user);
    }

    /**
     * Elimina un utente tramite il suo indirizzo email.
     *
     * @param email indirizzo email dell’utente da eliminare
     */
    @DeleteMapping("/delete/email/{email}")
    public void eliminaUtente(@PathVariable String email){
        userAccountService.deleteUserAccount(email);
    }

    /**
     * Aggiorna nome e cognome dell’utente specificato.
     *
     * @param email indirizzo email dell’utente da aggiornare
     * @param user oggetto {@link UserAccountDTO} con i nuovi dati
     * @return il {@link UserAccountDTO} aggiornato
     */
    @PutMapping("/update/nomecognome/{email}")
    public UserAccountDTO updateNomeCognome(@PathVariable String email, @RequestBody UserAccountDTO user){
        return userAccountService.updateUserInfo(email, user);
    }

    /**
     * Aggiorna l’indirizzo email dell’utente.
     *
     * @param email email attuale dell’utente
     * @param nuovaEmail nuova email da assegnare
     * @return messaggio di conferma
     */
    @PutMapping("update/email/{email}")
    public String updateEmail(@PathVariable String email, @RequestBody String nuovaEmail){
        return userAccountService.aggiornaEmailUtente(email, nuovaEmail);
    }

    /**
     * Aggiorna lo username dell’utente.
     *
     * @param email email attuale dell’utente
     * @param nuovoUsername nuovo username da assegnare
     * @return messaggio di conferma
     */
    @PutMapping("update/username/{email}")
    public String updateUsername(@PathVariable String email, @RequestBody String nuovoUsername){
        return userAccountService.aggiornaUsernameUtenteStream(email, nuovoUsername);
    }

    /**
     * Aggiorna la password dell’utente.
     *
     * @param email email attuale dell’utente
     * @param nuovaPassword nuova password da assegnare
     * @return messaggio di conferma
     */
    @PutMapping("update/password/{email}")
    public String updatePassword(@PathVariable String email, @RequestBody String nuovaPassword){
        return userAccountService.aggiornaPasswordUtente(email, nuovaPassword);
    }

    /**
     * Elimina tutti gli utenti dal sistema.
     */
    @DeleteMapping("/delete/all")
    public void deleteAllUser(){
        userAccountService.deleteAllUsers();
    }

    /**
     * Restituisce lo storico dei noleggi collegati all’utente specificato.
     *
     * @param email email dell’utente
     * @return lista di {@link StoricoNoleggioDTO} relativi all’utente
     */
    @GetMapping("/storiconoleggi/{email}")
    public List<StoricoNoleggioDTO> getStoricoNoleggiUtente(@PathVariable String email) {
        return userAccountService.getStoricoNoleggiUtente(email);
    }
}