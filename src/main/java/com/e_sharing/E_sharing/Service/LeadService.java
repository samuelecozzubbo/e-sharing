package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Repositories.LeadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    private final LeadRepository leadRepository;

    @Autowired
    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    // Metodi per gestire i lead

    // Recupera tutti i lead

    public List<Lead> getAllLeads() {
        return (List<Lead>) leadRepository.findAll();
    }
    // Recupera un lead per id
    public Optional<Lead> getLeadById(Long id) {
        return leadRepository.findById(id);
    }

    // Salva un nuovo lead
    public Lead saveLead(Lead lead) {
        return leadRepository.save(lead);
    }

    // Elimina un lead dato l'id
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

    // Aggiorna un lead (puoi personalizzare cosa aggiornare)
    @Transactional
    public Lead updateLead(Long id, Lead updatedLead) {
        return leadRepository.findById(id)
                .map(existingLead -> {
                    existingLead.setDataAcquisto(updatedLead.getDataAcquisto());
                    existingLead.setStatus(updatedLead.getStatus());
                    existingLead.setSconto(updatedLead.getSconto());
                    // Se vuoi aggiornare anche la relazione con UserAccount:
                    existingLead.setUserAccount(updatedLead.getUserAccount());
                    return leadRepository.save(existingLead);
                })
                .orElseThrow(() -> new RuntimeException("Lead non trovato con id: " + id));
    }

}
