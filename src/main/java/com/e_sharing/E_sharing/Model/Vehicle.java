package com.e_sharing.E_sharing.Model;
import com.e_sharing.E_sharing.Enum.VehicleType;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private VehicleType vehicleType;
    private Double costoNoleggio;
    private Double livelloBatteria;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "site_id")
    private Site site;

    //Costruttori
    public Vehicle() {}
    public Vehicle(VehicleType vehicleType, Double costoNoleggio, Double livelloBatteria, Site site) {
        this.vehicleType = vehicleType;
        this.costoNoleggio = costoNoleggio;
        this.livelloBatteria = livelloBatteria;
        this.site = site;
    }

    //Getter e Setter
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
}
