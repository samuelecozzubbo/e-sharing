package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "lead_vehicle")
public class LeadVehicle {

    @EmbeddedId
    private LeadVehicleId id;

    // Relazione con Lead
    @ManyToOne
    @MapsId("leadId")
    @JoinColumn(name = "lead_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lead lead;

    // Relazione con Vehicle
    @ManyToOne
    @MapsId("vehicleId")
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    // Attributi
    private double totale;
    private Date dataNoleggio;
    private int durataNoleggioGiorni;

    // Constructor
    public LeadVehicle() {}

    public LeadVehicle(Lead lead, Vehicle vehicle, double totale, Date dataNoleggio, int durataNoleggioGiorni) {
        this.lead = lead;
        this.vehicle = vehicle;
        this.totale = totale;
        this.dataNoleggio = dataNoleggio;
        this.durataNoleggioGiorni = durataNoleggioGiorni;
        this.id = new LeadVehicleId(lead.getId(), vehicle.getId());
    }

    // Getter & Setter

    public LeadVehicleId getId() {
        return id;
    }

    public void setId(LeadVehicleId id) {
        this.id = id;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
        if (this.id == null) this.id = new LeadVehicleId();
        this.id.setLeadId(lead.getId());
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        if (this.id == null) this.id = new LeadVehicleId();
        this.id.setVehicleId(vehicle.getId());
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public Date getDataNoleggio() {
        return dataNoleggio;
    }

    public void setDataNoleggio(Date dataNoleggio) {
        this.dataNoleggio = dataNoleggio;
    }

    public int getDurataNoleggioGiorni() {
        return durataNoleggioGiorni;
    }

    public void setDurataNoleggioGiorni(int durataNoleggioGiorni) {
        this.durataNoleggioGiorni = durataNoleggioGiorni;
    }
}
