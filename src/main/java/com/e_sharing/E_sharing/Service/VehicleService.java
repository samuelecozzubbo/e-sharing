package com.e_sharing.E_sharing.Service;

import com.e_sharing.E_sharing.DTO.VehicleDTO;
import com.e_sharing.E_sharing.Model.Vehicle;
import com.e_sharing.E_sharing.Repositories.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final GenericUtils genericUtils;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, ModelMapper modelMapper, GenericUtils genericUtils) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.genericUtils = genericUtils;
    }

    public List<VehicleDTO> getAllVehicle(){
        Iterable<Vehicle> veicoli = vehicleRepository.findAll();
        return genericUtils.iterableToListAndDTO(veicoli, VehicleDTO.class);
    }

    public Optional<VehicleDTO> findVehicleById(Long id){
        return vehicleRepository.findById(id).map(veicolo -> modelMapper.map(veicolo, VehicleDTO.class));
    }

    public Vehicle saveVehicle(VehicleDTO veicolo){
        return vehicleRepository.save(modelMapper.map(veicolo, Vehicle.class));
    }

    public void deleteVehicle(Long id){
        vehicleRepository.deleteById(id);
    }

}
