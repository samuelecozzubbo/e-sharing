package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.SiteDTO;
import com.e_sharing.E_sharing.Service.SiteService;
import com.e_sharing.E_sharing.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione delle operazioni relative ai Siti.
 * <p>
 * Espone endpoint per le principali operazioni CRUD sui Siti,
 * utilizzando il servizio {@link SiteService} per la logica di business.
 * Tutte le risposte sono in formato {@link SiteDTO}.
 * </p>
 *
 * <ul>
 *   <li>{@code GET /site} - Restituisce la lista di tutti i Siti</li>
 *   <li>{@code GET /site/find/{id}} - Restituisce un Sito tramite ID</li>
 *   <li>{@code POST /site/save} - Salva un nuovo Sito</li>
 *   <li>{@code DELETE /site/delete/{id}} - Elimina un Sito tramite ID</li>
 *   <li>{@code PUT /site/update/{id}} - Aggiorna un Sito esistente</li>
 * </ul>
 */
@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;

    /**
     * Restituisce la lista di tutti i Siti presenti nel sistema.
     *
     * @return lista di {@link SiteDTO}
     */
    @GetMapping
    public List<SiteDTO> getAllSite() {
        return siteService.getAllSite();
    }

    /**
     * Restituisce un Sito tramite il suo identificativo.
     *
     * @param id identificativo del Sito
     * @return un {@link Optional} contenente il {@link SiteDTO}, se trovato
     */
    @GetMapping("/find/{id}")
    public Optional<SiteDTO> getSiteById (@PathVariable Long id){
        return siteService.getSiteById(id);
    }

    /**
     * Salva un nuovo Sito nel sistema.
     *
     * @param site oggetto {@link SiteDTO} da salvare
     * @return messaggio di conferma
     */
    @PostMapping("/save")
    public String saveSite(@RequestBody SiteDTO site) {
        return siteService.saveSite(site);
    }

    /**
     * Elimina un Sito tramite il suo identificativo.
     *
     * @param id identificativo del Sito da eliminare
     * @return messaggio di conferma dell'eliminazione
     */
    @DeleteMapping("/delete/{id}")
    public String deleteSiteById(@PathVariable Long id) {
        return siteService.deleteSite(id);
    }

    /**
     * Aggiorna le informazioni di un Sito esistente.
     *
     * @param id identificativo del Sito da aggiornare
     * @param site oggetto {@link SiteDTO} con i nuovi dati
     * @return il {@link SiteDTO} aggiornato
     */
    @PutMapping("/update/{id}")
    public SiteDTO updateSite(@PathVariable Long id, @RequestBody SiteDTO site) {
        return siteService.updateSite(id, site);
    }

}