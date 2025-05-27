package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Service.UserAccountService;
import com.e_sharing.E_sharing.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping
    public List<UserAccountDTO> getAllUsers(){
        return userAccountService.getAllUserAccountsAuto();
    }

    @GetMapping("/find/{email}")
    public Optional<UserAccountDTO> findByEmail(@PathVariable String email) {
        return userAccountService.findUserByEmail(email);
    }

    @PostMapping("/registra")
    public UserAccount inserisciUtente(@RequestBody UserAccountDTO user){
        return userAccountService.saveUserAccount(user);
    }

    @DeleteMapping("/elimina/email/{email}")
    public void eliminaUtente(@PathVariable String email){
        userAccountService.deleteUserAccount(email);
    }

    @PutMapping("/update/noemcognome/{email}")
    public UserAccountDTO updateNomeCognome(@PathVariable String email, @RequestBody UserAccountDTO user){
        return userAccountService.updateUserInfo(email, user);
    }

    @PutMapping("update/email/{email}")
    public String upddateEmail(@PathVariable String email, @RequestBody String nuovaEmail){
        return userAccountService.aggiornaEmailUtente(email, nuovaEmail);
    }

    @PutMapping("update/username/{email}")
    public String upddateUsername(@PathVariable String email, @RequestBody String nuovoUsername){
        return userAccountService.aggiornaUsernameUtenteStream(email, nuovoUsername);
    }

    @PutMapping("update/password/{email}")
    public String upddatePassword(@PathVariable String email, @RequestBody String nuovaPassword){
        return userAccountService.aggiornaPasswordUtente(email, nuovaPassword);
    }

    @PostMapping("/login/{email}")
    public boolean login(@PathVariable String email, @RequestBody String password){
        return userAccountService.login(email, password);
    }

    @DeleteMapping("/elimina/all")
    public void deleteAllUser(){
        userAccountService.deleteAllUsers();
    }

}
