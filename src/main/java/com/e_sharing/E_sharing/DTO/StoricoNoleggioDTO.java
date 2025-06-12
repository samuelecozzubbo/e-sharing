package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Enum.LeadState;
import java.util.Date;

/**
 * Data Transfer Object (DTO) utilizzato per rappresentare lo storico di un noleggio di un veicolo.
 * <p>
 * Questo oggetto contiene le informazioni principali relative a un noleggio effettuato da un utente,
 * come il veicolo coinvolto, la data, la durata, il totale e lo stato del lead.
 * </p>
 *
 * <ul>
 *   <li>{@code vehicle}: veicolo noleggiato ({@link VehicleDTO})</li>
 *   <li>{@code dataNoleggio}: data di inizio del noleggio</li>
 *   <li>{@code durataNoleggioGiorni}: durata del noleggio in giorni</li>
 *   <li>{@code totale}: importo totale del noleggio</li>
 *   <li>{@code status}: stato del lead (valore dell'enum {@link LeadState})</li>
 * </ul>
 */
public class StoricoNoleggioDTO {
    private VehicleDTO vehicle;
    private Date dataNoleggio;
    private int durataNoleggioGiorni;
    private double totale;
    private LeadState status;

    /**
     * Costruttore vuoto per la creazione di un oggetto StoricoNoleggioDTO senza parametri.
     */
    public StoricoNoleggioDTO() {
    }

    /**
     * Costruttore per la creazione di un oggetto StoricoNoleggioDTO con parametri.
     *
     * @param vehicle veicolo noleggiato
     * @param dataNoleggio data di inizio noleggio
     * @param durataNoleggioGiorni durata in giorni del noleggio
     * @param totale importo totale del noleggio
     * @param status stato del lead
     */
    public StoricoNoleggioDTO(VehicleDTO vehicle, Date dataNoleggio, int durataNoleggioGiorni, double totale, LeadState status) {
        this.vehicle = vehicle;
        this.dataNoleggio = dataNoleggio;
        this.durataNoleggioGiorni = durataNoleggioGiorni;
        this.totale = totale;
        this.status = status;
    }

    /** Restituisce il veicolo noleggiato. */
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    /** Imposta il veicolo noleggiato. */
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    /** Restituisce la data di inizio del noleggio. */
    public Date getDataNoleggio() {
        return dataNoleggio;
    }

    /** Imposta la data di inizio del noleggio. */
    public void setDataNoleggio(Date dataNoleggio) {
        this.dataNoleggio = dataNoleggio;
    }

    /** Restituisce la durata del noleggio in giorni. */
    public int getDurataNoleggioGiorni() {
        return durataNoleggioGiorni;
    }

    /** Imposta la durata del noleggio in giorni. */
    public void setDurataNoleggioGiorni(int durataNoleggioGiorni) {
        this.durataNoleggioGiorni = durataNoleggioGiorni;
    }

    /** Restituisce l'importo totale del noleggio. */
    public double getTotale() {
        return totale;
    }

    /** Imposta l'importo totale del noleggio. */
    public void setTotale(double totale) {
        this.totale = totale;
    }

    /** Restituisce lo stato del lead. */
    public LeadState getStatus() {
        return status;
    }

    /** Imposta lo stato del lead. */
    public void setStatus(LeadState status) {
        this.status = status;
    }
}