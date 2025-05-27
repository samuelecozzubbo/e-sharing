package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lead")
public class LeadController {
    @Autowired
    private LeadService leadService;

    @GetMapping
    public List<LeadDTO> getAll() {
        return leadService.getAllLeadsGeneric();
    }

    @GetMapping("/find/{id}")
    public Optional<LeadDTO> getById(@PathVariable Long id){
        return leadService.getLeadById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody LeadDTO leadDTO){
        return leadService.saveLead(leadDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        leadService.deleteLead(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAll() {
        leadService.deleteAllLeads();
    }

    @PutMapping("/update/{id}")
    public LeadDTO updateLead(@PathVariable Long id, @RequestBody LeadDTO leadDTO){
        return leadService.updateLead(id, leadDTO);
    }
}