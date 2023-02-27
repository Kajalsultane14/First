package com.GroceriesSite.GroceriesStore.repositories;

import com.GroceriesSite.GroceriesStore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {



}
