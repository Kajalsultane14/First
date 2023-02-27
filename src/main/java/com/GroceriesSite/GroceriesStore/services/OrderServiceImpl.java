package com.GroceriesSite.GroceriesStore.services;

import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exceptions.*;
import com.GroceriesSite.GroceriesStore.models.OrderItemRequest;
import com.GroceriesSite.GroceriesStore.models.OrderItemResponse;
import com.GroceriesSite.GroceriesStore.models.OrderRequest;
import com.GroceriesSite.GroceriesStore.models.OrderResponse;

import java.util.List;

public interface OrderServiceImpl {


    OrderResponse addOrder(OrderRequest orderRequest) throws ProductNotAvailableException, ProductEfficiencyException, RateExceedException, HolidayException, OfferNotAvailableException, OfferExpiredException, OfferNotApplicableException, OfferNotAvailableException;


    List<OrderResponse> getAllOrder();
    public List<OrderItemResponse> getAllOrderItem();
    public OrderItemResponse getOrderItemById(Long id);

    public OrderResponse getOrderById(Long id);
    public OrderItemResponse addOrderItem(OrderItemRequest orderItemRequest);

}
