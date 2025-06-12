package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione delle operazioni relative ai Lead.
 * <p>
 * Espone endpoint per le principali operazioni CRUD sui Lead,
 * utilizzando il servizio {@link LeadService} per la logica di business.
 * Tutte le risposte sono in formato {@link LeadDTO}.
 * </p>
 *
 * <ul>
 *   <li>{@code GET /lead} - Restituisce la lista di tutti i Lead</li>
 *   <li>{@code GET /lead/find/{id}} - Restituisce un Lead tramite ID</li>
 *   <li>{@code POST /lead/save} - Salva un nuovo Lead</li>
 *   <li>{@code DELETE /lead/delete/{id}} - Elimina un Lead tramite ID</li>
 *   <li>{@code DELETE /lead/delete/all} - Elimina tutti i Lead</li>
 *   <li>{@code PUT /lead/update/{id}} - Aggiorna un Lead esistente</li>
 *   <li>{@code PUT /lead/cancel/{id}} - Annulla un Lead tramite ID</li>
 * </ul>
 */
@RestController
@RequestMapping("/lead")
public class LeadController {
    @Autowired
    private LeadService leadService;

    /**
     * Restituisce la lista di tutti i Lead presenti nel sistema.
     *
     * @return lista di {@link LeadDTO}
     */
    @GetMapping
    public List<LeadDTO> getAll() {
        return leadService.getAllLeadsGeneric();
    }

    /**
     * Restituisce un Lead tramite il suo identificativo.
     *
     * @param id identificativo del Lead
     * @return un {@link Optional} contenente il {@link LeadDTO}, se trovato
     */
    @GetMapping("/find/{id}")
    public Optional<LeadDTO> getById(@PathVariable Long id){
        return leadService.getLeadById(id);
    }

    /**
     * Salva un nuovo Lead nel sistema.
     *
     * @param leadDTO oggetto {@link LeadDTO} da salvare
     * @return messaggio di conferma
     */
    @PostMapping("/save")
    public String save(@RequestBody LeadDTO leadDTO){
        return leadService.saveLead(leadDTO);
    }

    /**
     * Elimina un Lead tramite il suo identificativo.
     *
     * @param id identificativo del Lead da eliminare
     */
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        leadService.deleteLead(id);
    }

    /**
     * Elimina tutti i Lead presenti nel sistema.
     */
    @DeleteMapping("/delete/all")
    public void deleteAll() {
        leadService.deleteAllLeads();
    }

    /**
     * Aggiorna le informazioni di un Lead esistente.
     *
     * @param id identificativo del Lead da aggiornare
     * @param leadDTO oggetto {@link LeadDTO} con i nuovi dati
     * @return il {@link LeadDTO} aggiornato
     */
    @PutMapping("/update/{id}")
    public LeadDTO updateLead(@PathVariable Long id, @RequestBody LeadDTO leadDTO){
        return leadService.updateLead(id, leadDTO);
    }

    /**
     * Annulla un Lead tramite il suo identificativo.
     *
     * @param id identificativo del Lead da annullare
     * @return messaggio di conferma dell'annullamento
     */
    @PutMapping("/cancel/{id}")
    public String cancelLead(@PathVariable Long id) {
        return leadService.cancelLead(id);
    }
}