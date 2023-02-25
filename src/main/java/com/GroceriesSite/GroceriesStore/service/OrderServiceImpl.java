package com.GroceriesSite.GroceriesStore.service;

import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exception.*;
import com.GroceriesSite.GroceriesStore.model.OrderItemResponse;
import com.GroceriesSite.GroceriesStore.model.OrderRequest;
import com.GroceriesSite.GroceriesStore.model.OrderResponse;

import java.util.List;

public interface OrderServiceImpl {


    OrderResponse addOrder(OrderRequest orderRequest) throws ProductNotAvailableException, ProductEfficiencyException, RateExceedException, HolidayException, OfferNotAvailableException, OfferExpiredException, OfferNotApplicableException, OfferNotAvailableException;


    List<OrderResponse> getAllOrder();
    public List<OrderItemResponse> getAllOrderItem();
    public OrderItemResponse getOrderItemById(Long id);

    public OrderResponse getOrderById(Long id);
    public OrderItem addOrderItem(OrderItem orderItem);

}
