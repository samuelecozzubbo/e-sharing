package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.SiteDTO;
import com.e_sharing.E_sharing.Service.SiteService;
import com.e_sharing.E_sharing.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @GetMapping
    public List<SiteDTO> getAllSite() {
        return siteService.getAllSite();
    }

    @GetMapping("/find/{id}")
    public Optional<SiteDTO> getSiteById (@PathVariable Long id){
        return siteService.getSiteById(id);
    }

    @PostMapping("/save")
    public String saveSite(@RequestBody SiteDTO site) {
        return siteService.saveSite(site);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSiteById(@PathVariable Long id) {
        siteService.deleteSite(id);
    }

    @PutMapping("/update/{id}")
    public SiteDTO updateSite(@PathVariable Long id, @RequestBody SiteDTO site) {
        return siteService.updateSite(id, site);
    }

}
