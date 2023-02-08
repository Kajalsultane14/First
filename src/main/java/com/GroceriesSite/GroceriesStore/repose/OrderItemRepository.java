package com.GroceriesSite.GroceriesStore.repose;

import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {



}
