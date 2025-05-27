package com.e_sharing.E_sharing.Controller;


import com.e_sharing.E_sharing.DTO.VehicleDTO;
import com.e_sharing.E_sharing.Model.Vehicle;
import com.e_sharing.E_sharing.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<VehicleDTO> getAllVehicle(){
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/find/{id}")
    public Optional<VehicleDTO> findVehicleById(@PathVariable Long id){
        return vehicleService.findVehicleById(id);
    }

    @PostMapping("/save")
    public Vehicle saveVehicle(@RequestBody VehicleDTO veicolo){
        return vehicleService.saveVehicle(veicolo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllVehicle(){
        vehicleService.deleteAllVehicle();
    }
}
