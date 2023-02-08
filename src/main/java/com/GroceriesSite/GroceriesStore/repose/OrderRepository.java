package com.GroceriesSite.GroceriesStore.repose;

import com.GroceriesSite.GroceriesStore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface OrderRepository extends JpaRepository<Order,Long> {



}
