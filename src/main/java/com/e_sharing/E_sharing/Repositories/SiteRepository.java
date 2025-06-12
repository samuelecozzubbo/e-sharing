package com.e_sharing.E_sharing.Repositories;

import com.e_sharing.E_sharing.Model.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per la gestione delle entità Site.
 * <p>
 * Estende {@link CrudRepository} per fornire le operazioni CRUD di base sui Site.
 * </p>
 */
@Repository
public interface SiteRepository extends CrudRepository<Site , Long> {
}