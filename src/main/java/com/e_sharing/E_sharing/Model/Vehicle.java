package com.e_sharing.E_sharing.Model;

import com.e_sharing.E_sharing.Enum.VehicleState;
import com.e_sharing.E_sharing.Enum.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entità che rappresenta un veicolo nella piattaforma di e-sharing.
 * <p>
 * Un veicolo è associato a un sito e può essere di diversi tipi,
 * con stato e informazioni come costo di noleggio e livello batteria.
 * </p>
 *
 * <ul>
 *   <li>{@code id}: identificativo univoco del veicolo</li>
 *   <li>{@code vehicleType}: tipo di veicolo ({@link VehicleType})</li>
 *   <li>{@code costoNoleggio}: costo per il noleggio del veicolo</li>
 *   <li>{@code livelloBatteria}: livello attuale della batteria</li>
 *   <li>{@code state}: stato attuale del veicolo ({@link VehicleState})</li>
 *   <li>{@code site}: riferimento al sito in cui è presente il veicolo</li>
 * </ul>
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {
    // Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private Double costoNoleggio;

    @Column(nullable = false)
    private Double livelloBatteria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleState state;

    // Relazione con Site
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site site;

    // Costruttori
    public Vehicle() {}

    public Vehicle(VehicleType vehicleType, Double costoNoleggio, Double livelloBatteria, Site site, VehicleState state) {
        this.vehicleType = vehicleType;
        this.costoNoleggio = costoNoleggio;
        this.livelloBatteria = livelloBatteria;
        this.site = site;
        this.state = state;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getCostoNoleggio() {
        return costoNoleggio;
    }

    public void setCostoNoleggio(Double costoNoleggio) {
        this.costoNoleggio = costoNoleggio;
    }

    public Double getLivelloBatteria() {
        return livelloBatteria;
    }

    public void setLivelloBatteria(Double livelloBatteria) {
        this.livelloBatteria = livelloBatteria;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public VehicleState getState() {
        return state;
    }

    public void setState(VehicleState state) {
        this.state = state;
    }
}