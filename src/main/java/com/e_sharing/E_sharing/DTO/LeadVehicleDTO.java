package com.e_sharing.E_sharing.DTO;

import java.util.Date;

/**
 * Data Transfer Object (DTO) utilizzato per rappresentare e trasferire i dati relativi al noleggio di un veicolo da parte di un lead.
 * <p>
 * Questo oggetto viene utilizzato per passare le informazioni necessarie durante le operazioni di noleggio veicolo.
 * </p>
 *
 * <ul>
 *   <li>{@code leadId}: identificativo del Lead che effettua il noleggio</li>
 *   <li>{@code vehicleId}: identificativo del Veicolo noleggiato</li>
 *   <li>{@code totale}: importo totale del noleggio</li>
 *   <li>{@code dataNoleggio}: data di inizio noleggio</li>
 *   <li>{@code durataNoleggioGiorni}: durata del noleggio in giorni</li>
 * </ul>
 */
public class LeadVehicleDTO {
    private Long leadId;
    private Long vehicleId;
    private double totale;
    private Date dataNoleggio;
    private int durataNoleggioGiorni;

    /**
     * Restituisce l'identificativo del Lead che effettua il noleggio.
     *
     * @return id del Lead
     */
    public Long getLeadId() {
        return leadId;
    }

    /**
     * Imposta l'identificativo del Lead che effettua il noleggio.
     *
     * @param leadId id del Lead
     */
    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    /**
     * Restituisce l'identificativo del Veicolo noleggiato.
     *
     * @return id del Veicolo
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * Imposta l'identificativo del Veicolo noleggiato.
     *
     * @param vehicleId id del Veicolo
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Restituisce l'importo totale del noleggio.
     *
     * @return totale del noleggio
     */
    public double getTotale() {
        return totale;
    }

    /**
     * Imposta l'importo totale del noleggio.
     *
     * @param totale importo totale
     */
    public void setTotale(double totale) {
        this.totale = totale;
    }

    /**
     * Restituisce la data di inizio noleggio.
     *
     * @return data di noleggio
     */
    public Date getDataNoleggio() {
        return dataNoleggio;
    }

    /**
     * Imposta la data di inizio noleggio.
     *
     * @param dataNoleggio data di noleggio
     */
    public void setDataNoleggio(Date dataNoleggio) {
        this.dataNoleggio = dataNoleggio;
    }

    /**
     * Restituisce la durata del noleggio in giorni.
     *
     * @return durata in giorni
     */
    public int getDurataNoleggioGiorni() {
        return durataNoleggioGiorni;
    }

    /**
     * Imposta la durata del noleggio in giorni.
     *
     * @param durataNoleggioGiorni durata in giorni
     */
    public void setDurataNoleggioGiorni(int durataNoleggioGiorni) {
        this.durataNoleggioGiorni = durataNoleggioGiorni;
    }
}