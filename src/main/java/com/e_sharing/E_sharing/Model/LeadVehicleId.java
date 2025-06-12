package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe che rappresenta la chiave primaria composta per l'entità LeadVehicle.
 * <p>
 * Questa classe viene annotata con {@link Embeddable} per essere utilizzata come chiave primaria composta in JPA.
 * È obbligatorio implementare i metodi {@code equals} e {@code hashCode} per garantire il corretto funzionamento delle operazioni
 * di persistenza e confronto delle entità con chiave composta.
 * </p>
 *
 * <ul>
 *   <li>{@code leadId}: identificativo del lead</li>
 *   <li>{@code vehicleId}: identificativo del veicolo</li>
 * </ul>
 */
@Embeddable
public class LeadVehicleId implements Serializable {
    @Column(name = "lead_id")
    private Long leadId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * Costruttore vuoto richiesto da JPA.
     */
    public LeadVehicleId() {}

    /**
     * Costruttore completo per LeadVehicleId.
     * @param leadId identificativo del lead
     * @param vehicleId identificativo del veicolo
     */
    public LeadVehicleId(Long leadId, Long vehicleId) {
        this.leadId = leadId;
        this.vehicleId = vehicleId;
    }

    /**
     * Restituisce l'identificativo del lead.
     * @return leadId
     */
    public Long getLeadId() {
        return leadId;
    }

    /**
     * Imposta l'identificativo del lead.
     * @param leadId identificativo del lead
     */
    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    /**
     * Restituisce l'identificativo del veicolo.
     * @return vehicleId
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * Imposta l'identificativo del veicolo.
     * @param vehicleId identificativo del veicolo
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Override del metodo equals per confrontare due LeadVehicleId in base ai campi leadId e vehicleId.
     * Necessario per le chiavi primarie composte in JPA.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeadVehicleId)) return false;
        LeadVehicleId that = (LeadVehicleId) o;
        return Objects.equals(leadId, that.leadId) &&
                Objects.equals(vehicleId, that.vehicleId);
    }

    /**
     * Override di hashCode: necessario per le chiavi primarie composte in JPA,
     * garantisce che due oggetti con stessi valori abbiano lo stesso hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(leadId, vehicleId);
    }
}