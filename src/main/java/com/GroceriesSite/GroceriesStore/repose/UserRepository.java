package com.GroceriesSite.GroceriesStore.repose;

import com.GroceriesSite.GroceriesStore.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByUsername(String username);
}
