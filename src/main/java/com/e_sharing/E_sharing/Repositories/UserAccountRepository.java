package com.e_sharing.E_sharing.Repositories;

import com.e_sharing.E_sharing.Model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
}
