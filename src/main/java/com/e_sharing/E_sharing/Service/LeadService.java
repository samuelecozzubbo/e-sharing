package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Enum.LeadState;
import com.e_sharing.E_sharing.Enum.VehicleState;
import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Model.LeadVehicle;
import com.e_sharing.E_sharing.Model.UserAccount;
import com.e_sharing.E_sharing.Model.Vehicle;
import com.e_sharing.E_sharing.Repositories.LeadRepository;
import com.e_sharing.E_sharing.Repositories.LeadVehicleRepository;
import com.e_sharing.E_sharing.Repositories.UserAccountRepository;
import com.e_sharing.E_sharing.Repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    // Repository per gestire i lead
    private final LeadRepository leadRepository;
    // Repository per gestire gli utenti
    private final UserAccountRepository userAccountRepository;
    // ModelMapper per la conversione tra entità e DTO
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;
    // Repository per gestire le associazioni tra lead e veicoli
    private final LeadVehicleRepository leadVehicleRepository;
    // Repository per gestire i veicoli
    private final VehicleRepository vehicleRepository;

    @Autowired // Iniezione delle dipendenze tramite il costruttore
    public LeadService(LeadRepository leadRepository, UserAccountRepository userAccountRepository, ModelMapper modelMapper, GenericUtils genericUtils, LeadVehicleRepository leadVehicleRepository, VehicleRepository vehicleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
        this.leadRepository = leadRepository;
        this.genericUtils = genericUtils;
        this.leadVehicleRepository = leadVehicleRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // Metodi per gestire i lead

    // Recupera tutti i lead
    public List<LeadDTO> getAllLeads() {
        List<LeadDTO> DTOList = new ArrayList<>();
        leadRepository.findAll().forEach(lead -> DTOList.add(modelMapper.map(lead, LeadDTO.class)));
        return DTOList;
    }

    // Recupera tutti i lead con metodo generico
    public List<LeadDTO> getAllLeadsGeneric() {
        return genericUtils.iterableToListAndDTO(leadRepository.findAll(), LeadDTO.class);
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

    // Elimina tutti i lead
    public void deleteAllLeads() {
        leadRepository.deleteAll();
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

    // Imposta lo stato di un lead come "CANCELLED" e aggiorna lo stato dei veicoli associati a "DISPONIBILE"
    @Transactional
    public String cancelLead(Long id) {
        // Controlla se il lead esiste
        Optional<Lead> optionalLead = leadRepository.findById(id);
        if (optionalLead.isEmpty()) {
            return "Lead non trovato con id: " + id;
        }
        Lead lead = optionalLead.get();
        // Se il lead esiste imposta lo stato a CANCELLED
        lead.setStatus(LeadState.CANCELLED);

        // Recupera tutti i veicoli associati al lead
        List<LeadVehicle> leadVehicles = leadVehicleRepository.findByLead(lead);

        // Imposta lo stato dei veicoli associati al lead cancellato a DISPONIBILE
        for (LeadVehicle lv : leadVehicles) {
            Vehicle v = lv.getVehicle();
            v.setState(VehicleState.DISPONIBILE);
            vehicleRepository.save(v);
        }

        leadRepository.save(lead);

        return "Prenotazione annullata con successo (Lead id: " + id + ")";
    }
}