package com.e_sharing.E_sharing.Repositories;

import com.e_sharing.E_sharing.Model.Lead;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per la gestione delle entità Lead.
 * <p>
 * Estende {@link CrudRepository} per fornire le operazioni CRUD di base sui Lead.
 * </p>
 */
@Repository
public interface LeadRepository extends CrudRepository<Lead, Long> {
}