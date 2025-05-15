package com.e_sharing.E_sharing.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "lead_vehicle")
public class LeadVehicle {
    //Chiavi primarie ed esterne di lead e vehicle
    @Id
    @ManyToOne
    @JoinColumn(name = "lead_id", referencedColumnName = "id")
    private Lead lead;
    @Id
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    //Attributi
    private double totale;
    private Date dataNoleggio;

    //Constructor
    public LeadVehicle() {}

    public LeadVehicle(Lead lead, Vehicle vehicle, double totale, Date dataNoleggio) {
        this.lead = lead;
        this.vehicle = vehicle;
        this.totale = totale;
        this.dataNoleggio = dataNoleggio;
    }

    //Getter e Setter
    public Lead getLead() {
        return lead;
    }
    public void setLead(Lead lead) {
        this.lead = lead;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
}
