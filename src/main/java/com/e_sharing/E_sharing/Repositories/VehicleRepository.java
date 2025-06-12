package com.e_sharing.E_sharing.Repositories;

import com.e_sharing.E_sharing.Enum.VehicleState;
import com.e_sharing.E_sharing.Model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione delle entità Vehicle.
 * <p>
 * Estende {@link CrudRepository} per fornire le operazioni CRUD di base sui Vehicle
 * e metodi di ricerca personalizzati.
 * </p>
 */
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    /**
     * Restituisce la lista dei veicoli con uno stato specifico.
     *
     * @param vehicleState stato del veicolo (VehicleState)
     * @return lista di veicoli con lo stato richiesto
     */
    List<Vehicle> findByState(VehicleState vehicleState);
}