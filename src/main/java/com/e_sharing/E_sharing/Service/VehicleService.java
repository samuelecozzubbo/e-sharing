package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.VehicleDTO;
import com.e_sharing.E_sharing.Enum.VehicleState;
import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Model.LeadVehicle;
import com.e_sharing.E_sharing.Model.Site;
import com.e_sharing.E_sharing.Model.Vehicle;
import com.e_sharing.E_sharing.Repositories.LeadRepository;
import com.e_sharing.E_sharing.Repositories.SiteRepository;
import com.e_sharing.E_sharing.Repositories.VehicleRepository;
import com.e_sharing.E_sharing.Repositories.LeadVehicleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service per la gestione dei veicoli e delle operazioni di noleggio.
 *
 * Funzionalità principali:
 * <ul>
 *     <li>Recupero di tutti i veicoli o di uno specifico veicolo tramite ID</li>
 *     <li>Salvataggio di nuovi veicoli associati a un sito</li>
 *     <li>Eliminazione di un veicolo o di tutti i veicoli</li>
 *     <li>Conteggio totale dei veicoli esistenti</li>
 *     <li>Aggiornamento delle informazioni di un veicolo</li>
 *     <li>Recupero dei veicoli attualmente disponibili</li>
 *     <li>Noleggio di un veicolo con gestione automatica degli sconti in base alla durata</li>
 *     <li>Segnalazione di un veicolo come guasto (stato MANUTENZIONE)</li>
 *     <li>Aggiornamento pianificato dello stato dei veicoli al termine del noleggio (automazione giornaliera)</li>
 * </ul>
 */
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;
    private final LeadVehicleRepository leadVehicleRepository;
    private final LeadRepository leadRepository;
    private final SiteRepository siteRepository;

    @Autowired
    public VehicleService(
            VehicleRepository vehicleRepository,
            ModelMapper modelMapper,
            GenericUtils genericUtils,
            LeadVehicleRepository leadVehicleRepository,
            LeadRepository leadRepository,
            SiteRepository siteRepository
    ) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
        this.leadVehicleRepository = leadVehicleRepository;
        this.leadRepository = leadRepository;
        this.siteRepository = siteRepository;
    }

    /**
     * Restituisce tutti i veicoli come lista di VehicleDTO.
     *
     * @return lista di tutti i veicoli presenti nel sistema convertiti in DTO.
     */
    public List<VehicleDTO> getAllVehicle() {
        Iterable<Vehicle> veicoli = vehicleRepository.findAll();
        return genericUtils.iterableToListAndDTO(veicoli, VehicleDTO.class);
    }

    /**
     * Restituisce un veicolo specifico tramite ID.
     *
     * @param id L'id del veicolo da trovare.
     * @return Optional contenente il VehicleDTO se trovato, altrimenti vuoto.
     */
    public Optional<VehicleDTO> findVehicleById(Long id) {
        return vehicleRepository.findById(id).map(veicolo -> modelMapper.map(veicolo, VehicleDTO.class));
    }

    /**
     * Salva un nuovo veicolo associandolo a un sito e lo imposta come DISPONIBILE.
     *
     * @param veicolo DTO del veicolo da salvare.
     * @return Messaggio di conferma del salvataggio.
     */
    public String saveVehicle(VehicleDTO veicolo) {
        Vehicle v = modelMapper.map(veicolo, Vehicle.class);
        Site site = siteRepository.findById(veicolo.getSiteId())
                .orElseThrow(() -> new RuntimeException("Site non trovato con id: " + veicolo.getSiteId()));
        v.setSite(site);
        v.setState(VehicleState.DISPONIBILE);
        vehicleRepository.save(v);
        return "Veicolo salvato con successo!";
    }

    /**
     * Elimina un veicolo tramite id.
     *
     * @param id ID del veicolo da eliminare.
     */
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    /**
     * Elimina tutti i veicoli.
     */
    public void deleteAllVehicle() {
        vehicleRepository.deleteAll();
    }

    /**
     * Restituisce il numero totale di veicoli.
     *
     * @return Numero totale dei veicoli.
     */
    public long getCountVehicle() {
        return vehicleRepository.count();
    }

    /**
     * Aggiorna le informazioni di un veicolo.
     *
     * @param id      ID del veicolo da aggiornare.
     * @param vehicle DTO con i nuovi dati del veicolo.
     * @return VehicleDTO aggiornato.
     */
    @Transactional
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicle) {
        return vehicleRepository.findById(id).map(vehicleToUpdate -> {
            vehicleToUpdate.setVehicleType(vehicle.getVehicleType());
            vehicleToUpdate.setCostoNoleggio(vehicle.getCostoNoleggio());
            vehicleToUpdate.setLivelloBatteria(vehicle.getLivelloBatteria());
            vehicleToUpdate.setState(vehicle.getState());
            vehicleToUpdate.setSite(siteRepository.findById(vehicle.getSiteId())
                    .orElseThrow(() -> new RuntimeException("Sito non trovato con ID: " + vehicle.getSiteId())));
            return modelMapper.map(vehicleRepository.save(vehicleToUpdate), VehicleDTO.class);
        }).orElseThrow(() -> new RuntimeException("Veicolo non trovato con ID: " + id));
    }

    /**
     * Restituisce tutti i veicoli attualmente disponibili.
     *
     * @return Lista di VehicleDTO dei veicoli disponibili.
     */
    public List<VehicleDTO> getVeicoliDisponibiliOra() {
        List<Vehicle> disponibili = vehicleRepository.findByState(VehicleState.DISPONIBILE);
        return disponibili.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Noleggia un veicolo per un lead, applicando eventuali sconti in base alla durata.
     *
     * @param vehicleId     ID del veicolo da noleggiare.
     * @param leadId        ID dell'utente/lead che noleggia.
     * @param dataNoleggio  Data di inizio noleggio.
     * @param durataGiorni  Durata del noleggio in giorni.
     * @return Messaggio di conferma con il totale e lo sconto applicato.
     */
    @Transactional
    public String noleggiaVeicolo(Long vehicleId, Long leadId, Date dataNoleggio, int durataGiorni) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Veicolo non trovato"));
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead non trovato"));

        // Controlla che il veicolo sia disponibile
        if (vehicle.getState() == VehicleState.NOLEGGIATO) {
            throw new IllegalStateException("Veicolo già noleggiato!");
        }

        // Calcola sconto in base ai giorni di noleggio
        double sconto = 0.0;
        if (durataGiorni >= 10) {
            sconto = 0.30;
        } else if (durataGiorni >= 6) {
            sconto = 0.20;
        } else if (durataGiorni >= 3) {
            sconto = 0.10;
        }

        double totaleScontato = vehicle.getCostoNoleggio() * durataGiorni * (1 - sconto);

        LeadVehicle leadVehicle = new LeadVehicle(lead, vehicle, totaleScontato, dataNoleggio, durataGiorni);
        leadVehicleRepository.save(leadVehicle);

        vehicle.setState(VehicleState.NOLEGGIATO);
        vehicleRepository.save(vehicle);

        return "Veicolo noleggiato con successo! Totale: " + String.format("%.2f", totaleScontato) + "€ (sconto applicato: " + (sconto * 100) + "%)";
    }

    /**
     * Segnala un veicolo come guasto, aggiornandone lo stato a MANUTENZIONE.
     *
     * @param vehicleId ID del veicolo da segnalare come guasto.
     * @return Messaggio di conferma dell'aggiornamento di stato.
     */
    public String veicoloGuasto(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Veicolo non trovato con ID: " + vehicleId));
        vehicle.setState(VehicleState.MANUTENZIONE);
        vehicleRepository.save(vehicle);
        return "Stato del veicolo aggiornato a MANUTENZIONE.";
    }

    /**
     * Aggiorna ogni giorno lo stato dei veicoli, rendendoli DISPONIBILE se il noleggio è terminato.
     * Questa funzione è pianificata per l'esecuzione automatica ogni giorno a mezzanotte.
     */
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void aggiornaStatoVeicoli() {
        Date now = new Date();
        List<LeadVehicle> leadVehicleList = (List<LeadVehicle>) leadVehicleRepository.findAll();
        for (LeadVehicle lv : leadVehicleList) {
            Date inizio = lv.getDataNoleggio();
            long durataMs = (long) lv.getDurataNoleggioGiorni() * 24 * 60 * 60 * 1000;
            Date fine = new Date(inizio.getTime() + durataMs);
            Vehicle v = lv.getVehicle();
            if (now.after(fine) && v.getState() == VehicleState.NOLEGGIATO) {
                v.setState(VehicleState.DISPONIBILE);
                vehicleRepository.save(v);
            }
        }
    }
}