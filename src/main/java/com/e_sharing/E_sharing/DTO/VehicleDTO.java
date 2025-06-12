package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Enum.VehicleState;
import com.e_sharing.E_sharing.Enum.VehicleType;

/**
 * Data Transfer Object (DTO) utilizzato per rappresentare e trasferire i dati relativi a un veicolo.
 * <p>
 * Questo oggetto contiene le informazioni principali di un veicolo,
 * senza esporre direttamente l'entità del dominio.
 * </p>
 *
 * <ul>
 *   <li>{@code id}: identificativo univoco del veicolo</li>
 *   <li>{@code vehicleType}: tipo di veicolo ({@link VehicleType})</li>
 *   <li>{@code costoNoleggio}: costo del noleggio per il veicolo</li>
 *   <li>{@code livelloBatteria}: livello attuale della batteria del veicolo</li>
 *   <li>{@code state}: stato attuale del veicolo ({@link VehicleState})</li>
 *   <li>{@code siteId}: identificativo del sito associato al veicolo</li>
 * </ul>
 */
public class VehicleDTO {
    private Long id;
    private VehicleType vehicleType;
    private Double costoNoleggio;
    private Double livelloBatteria;
    private VehicleState state;
    private Long siteId;

    /**
     * Costruttore vuoto per la creazione di un oggetto VehicleDTO senza parametri.
     */
    public VehicleDTO(){

    }

    /**
     * Costruttore per la creazione di un oggetto VehicleDTO con parametri.
     *
     * @param id identificativo del veicolo
     * @param vehicleType tipo di veicolo
     * @param costoNoleggio costo del noleggio
     * @param livelloBatteria livello della batteria
     * @param state stato del veicolo
     * @param siteId identificativo del sito associato
     */
    public VehicleDTO(Long id, VehicleType vehicleType, Double costoNoleggio, Double livelloBatteria, VehicleState state, long siteId) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.costoNoleggio = costoNoleggio;
        this.livelloBatteria = livelloBatteria;
        this.state = state;
        this.siteId = siteId;
    }

    /**
     * Restituisce l'identificativo del veicolo.
     * @return id del veicolo
     */
    public Long getId(){
        return id;
    }

    /**
     * Imposta l'identificativo del veicolo.
     * @param id id del veicolo
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Restituisce il tipo di veicolo.
     * @return tipo di veicolo
     */
    public VehicleType getVehicleType(){
        return vehicleType;
    }

    /**
     * Imposta il tipo di veicolo.
     * @param vehicleType tipo di veicolo
     */
    public void setVehicleType(VehicleType vehicleType){
        this.vehicleType = vehicleType;
    }

    /**
     * Restituisce il costo del noleggio.
     * @return costo del noleggio
     */
    public Double getCostoNoleggio(){
        return costoNoleggio;
    }

    /**
     * Imposta il costo del noleggio.
     * @param costoNoleggio costo del noleggio
     */
    public void setCostoNoleggio(Double costoNoleggio){
        this.costoNoleggio = costoNoleggio;
    }

    /**
     * Restituisce il livello attuale della batteria.
     * @return livello della batteria
     */
    public Double getLivelloBatteria(){
        return livelloBatteria;
    }

    /**
     * Imposta il livello attuale della batteria.
     * @param livelloBatteria livello della batteria
     */
    public void setLivelloBatteria(Double livelloBatteria){
        this.livelloBatteria = livelloBatteria;
    }

    /**
     * Restituisce lo stato del veicolo.
     * @return stato del veicolo
     */
    public VehicleState getState() {
        return state;
    }

    /**
     * Imposta lo stato del veicolo.
     * @param state stato del veicolo
     */
    public void setState(VehicleState state) {
        this.state = state;
    }

    /**
     * Restituisce l'identificativo del sito associato.
     * @return id del sito
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * Imposta l'identificativo del sito associato.
     * @param siteId id del sito
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}