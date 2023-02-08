package com.GroceriesSite.GroceriesStore.service;

import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exception.HolidayException;
import com.GroceriesSite.GroceriesStore.exception.ProductEfficiencyException;
import com.GroceriesSite.GroceriesStore.exception.ProductNotAvailableException;
import com.GroceriesSite.GroceriesStore.exception.RateExceedException;

import java.util.List;

public interface OrderServiceInterface {


    Order addOrder(Order order) throws ProductNotAvailableException, ProductEfficiencyException, RateExceedException, HolidayException;


    List<Order> getAllOrder();
    public List<OrderItem> getAllOrderItem();
    public OrderItem getOrderItemById(Long id);

    public Order getOrderById(Long id);
    public OrderItem addOrderItem(OrderItem orderItem);

}
