package com.e_sharing.E_sharing.Controller;

import com.e_sharing.E_sharing.DTO.LeadVehicleDTO;
import com.e_sharing.E_sharing.DTO.VehicleDTO;
import com.e_sharing.E_sharing.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per la gestione delle operazioni relative ai veicoli.
 * <p>
 * Espone endpoint per le principali operazioni CRUD sui veicoli,
 * per la gestione del noleggio, la visualizzazione dei veicoli disponibili
 * e la segnalazione di veicoli guasti.
 * Utilizza il servizio {@link VehicleService} per la logica di business.
 * Tutte le risposte sono in formato {@link VehicleDTO} o {@link LeadVehicleDTO}.
 * </p>
 *
 * <ul>
 *   <li>{@code GET /vehicle} - Restituisce la lista di tutti i veicoli</li>
 *   <li>{@code GET /vehicle/find/{id}} - Restituisce un veicolo tramite ID</li>
 *   <li>{@code POST /vehicle/save} - Salva un nuovo veicolo</li>
 *   <li>{@code DELETE /vehicle/delete/{id}} - Elimina un veicolo tramite ID</li>
 *   <li>{@code DELETE /vehicle/delete/all} - Elimina tutti i veicoli</li>
 *   <li>{@code GET /vehicle/count} - Restituisce il numero totale di veicoli</li>
 *   <li>{@code PUT /vehicle/update/{id}} - Aggiorna i dati di un veicolo</li>
 *   <li>{@code GET /vehicle/disponibili} - Restituisce la lista dei veicoli disponibili in questo momento</li>
 *   <li>{@code PUT /vehicle/guasto/{id}} - Segnala un veicolo come guasto (stato MANUTENZIONE)</li>
 *   <li>{@code POST /vehicle/noleggia} - Esegue il noleggio di un veicolo</li>
 * </ul>
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Restituisce la lista di tutti i veicoli presenti nel sistema.
     *
     * @return lista di {@link VehicleDTO}
     */
    @GetMapping
    public List<VehicleDTO> getAllVehicle(){
        return vehicleService.getAllVehicle();
    }

    /**
     * Restituisce un veicolo tramite il suo identificativo.
     *
     * @param id identificativo del veicolo
     * @return un {@link Optional} contenente il {@link VehicleDTO}, se trovato
     */
    @GetMapping("/find/{id}")
    public Optional<VehicleDTO> findVehicleById(@PathVariable Long id){
        return vehicleService.findVehicleById(id);
    }

    /**
     * Salva un nuovo veicolo nel sistema.
     *
     * @param veicolo oggetto {@link VehicleDTO} da salvare
     * @return messaggio di conferma del salvataggio
     */
    @PostMapping("/save")
    public String saveVehicle(@RequestBody VehicleDTO veicolo){
        return vehicleService.saveVehicle(veicolo);
    }

    /**
     * Elimina un veicolo tramite il suo identificativo.
     *
     * @param id identificativo del veicolo da eliminare
     */
    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }

    /**
     * Elimina tutti i veicoli presenti nel sistema.
     */
    @DeleteMapping("/delete/all")
    public void deleteAllVehicle(){
        vehicleService.deleteAllVehicle();
    }

    /**
     * Restituisce il numero totale di veicoli presenti nel sistema.
     *
     * @return numero totale di veicoli
     */
    @GetMapping("/count")
    public long getCountVehicle() {
        return vehicleService.getCountVehicle();
    }

    /**
     * Aggiorna le informazioni di un veicolo esistente.
     *
     * @param id identificativo del veicolo da aggiornare
     * @param vehicle oggetto {@link VehicleDTO} con i nuovi dati
     * @return il {@link VehicleDTO} aggiornato
     */
    @PutMapping("/update/{id}")
    public VehicleDTO updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    /**
     * Restituisce la lista dei veicoli disponibili in questo momento.
     *
     * @return lista di {@link VehicleDTO} disponibili
     */
    @GetMapping("/disponibili")
    public List<VehicleDTO> getVeicoliDisponibiliOra() {
        return vehicleService.getVeicoliDisponibiliOra();
    }

    /**
     * Segnala un veicolo come guasto, aggiornandone lo stato a MANUTENZIONE.
     *
     * @param id identificativo del veicolo da segnalare come guasto
     * @return messaggio di conferma dell'aggiornamento di stato
     */
    @PutMapping("/guasto/{id}")
    public String setGuasto(@PathVariable Long id) {
        return vehicleService.veicoloGuasto(id);
    }

    /**
     * Esegue il noleggio di un veicolo.
     *
     * @param leadVehicleDTO oggetto {@link LeadVehicleDTO} contenente i dati per il noleggio
     * @return messaggio di conferma del noleggio
     */
    @PostMapping("/noleggia")
    public String noleggiaVeicolo(@RequestBody LeadVehicleDTO leadVehicleDTO) {
        return vehicleService.noleggiaVeicolo(
                leadVehicleDTO.getVehicleId(),
                leadVehicleDTO.getLeadId(),
                leadVehicleDTO.getDataNoleggio(),
                leadVehicleDTO.getDurataNoleggioGiorni()
        );
    }
}