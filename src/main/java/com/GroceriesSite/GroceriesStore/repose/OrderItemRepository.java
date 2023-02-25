package com.GroceriesSite.GroceriesStore.repose;

import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


}
