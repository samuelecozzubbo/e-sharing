package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.Service.UserAccountService;
import com.e_sharing.E_sharing.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping
    public List<UserAccountDTO> getAllUsers(){
        return userAccountService.getAllUserAccountsAuto();
    }
}
