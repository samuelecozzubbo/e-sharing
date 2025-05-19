package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Repositories.LeadRepository;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadService {
    private final LeadRepository leadRepository;
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LeadService(LeadRepository leadRepository, UserAccountRepository userAccountRepository, ModelMapper modelMapper) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
        this.leadRepository = leadRepository;
    }

    // Metodi per gestire i lead

    // Recupera tutti i lead
    public List<LeadDTO> getAllLeads() {
        List<LeadDTO> DTOList = new ArrayList<>();
        leadRepository.findAll().forEach(lead -> DTOList.add(modelMapper.map(lead, LeadDTO.class)));
        return DTOList;
    }
    // Recupera un lead per id
    public Optional<LeadDTO> getLeadById(Long id) {
        return leadRepository.findById(id)
                .map(lead -> modelMapper.map(lead, LeadDTO.class));
    }


    // Salva un nuovo lead
    @Transactional
    public String saveLead(LeadDTO leadDTO) {
        Lead lead = modelMapper.map(leadDTO, Lead.class);
        // Collegare l'utente manualmente se LeadDTO contiene solo l'email
        UserAccount user = userAccountRepository.findById(leadDTO.getUserEmail())
                .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + leadDTO.getUserEmail()));
        lead.setUserAccount(user);
        Lead savedLead = leadRepository.save(lead);
        return "Lead salvato con id: " + savedLead.getId();
    }


    // Elimina un lead dato l'id
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

    // Aggiorna un lead
    @Transactional
    public LeadDTO updateLead(Long id, LeadDTO leadDTO) {
        return leadRepository.findById(id)
                .map(leadTrovato -> {
                    leadTrovato.setDataAcquisto(leadDTO.getDataAcquisto());
                    leadTrovato.setStatus(leadDTO.getStatus());
                    leadTrovato.setSconto(leadDTO.getSconto());
                    leadTrovato.setUserAccount(userAccountRepository.findById(leadDTO.getUserEmail())
                            .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + leadDTO.getUserEmail())));

                    Lead updated = leadRepository.save(leadTrovato);
                    return modelMapper.map(updated, LeadDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Lead non trovato con id: " + id));
    }


}
