package com.e_sharing.E_sharing.DTO;

import com.e_sharing.E_sharing.Enum.VehicleType;

public class VehicleDTO {
    private Long id;
    private VehicleType vehicleType;
    private Double costoNoleggio;
    private Double livelloBatteria;

    public VehicleDTO(){

    }
    public VehicleDTO(Long id, VehicleType vehicleType, Double costoNoleggio, Double livelloBatteria){
        this.id = id;
        this.vehicleType = vehicleType;
        this.costoNoleggio = costoNoleggio;
        this.livelloBatteria = livelloBatteria;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType){
        this.vehicleType = vehicleType;
    }

    public Double getCostoNoleggio(){
        return costoNoleggio;
    }

    public void setCostoNoleggio(Double costoNoleggio){
        this.costoNoleggio = costoNoleggio;
    }

    public Double getLivelloBatteria(){
        return livelloBatteria;
    }

    public void setLivelloBatteria(Double livelloBatteria){
        this.livelloBatteria = livelloBatteria;
    }
}
