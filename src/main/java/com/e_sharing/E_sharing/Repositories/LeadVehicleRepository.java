package com.e_sharing.E_sharing.Repositories;

import com.e_sharing.E_sharing.Model.Lead;
import com.e_sharing.E_sharing.Model.LeadVehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione delle associazioni tra Lead e Vehicle.
 * <p>
 * Estende {@link CrudRepository} per fornire le operazioni CRUD di base sulle associazioni LeadVehicle.
 * </p>
 */
@Repository
public interface LeadVehicleRepository extends CrudRepository<LeadVehicle, Long> {

    /**
     * Restituisce tutte le associazioni LeadVehicle per un determinato utente (tramite email).
     *
     * @param email email dell'utente
     * @return lista di associazioni LeadVehicle
     */
    List<LeadVehicle> findAllByLead_UserAccount_Email(String email);

    /**
     * Restituisce tutte le associazioni LeadVehicle per un determinato Lead.
     *
     * @param lead oggetto Lead
     * @return lista di associazioni LeadVehicle
     */
    List<LeadVehicle> findByLead(Lead lead);
}