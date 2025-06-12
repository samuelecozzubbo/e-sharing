package com.e_sharing.E_sharing.Repositories;

import com.e_sharing.E_sharing.Model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per la gestione delle entità UserAccount.
 * <p>
 * Estende {@link CrudRepository} per fornire le operazioni CRUD di base sugli UserAccount.
 * </p>
 */
@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
}