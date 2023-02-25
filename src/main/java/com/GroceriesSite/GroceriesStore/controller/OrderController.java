package com.GroceriesSite.GroceriesStore.controller;

import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exception.*;
import com.GroceriesSite.GroceriesStore.model.OrderItemRequest;
import com.GroceriesSite.GroceriesStore.model.OrderItemResponse;
import com.GroceriesSite.GroceriesStore.model.OrderRequest;
import com.GroceriesSite.GroceriesStore.model.OrderResponse;
import com.GroceriesSite.GroceriesStore.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/coder")
@Validated
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceInterface;

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @PostMapping("/save")
    public ResponseEntity<OrderResponse> addOrder(@Valid @RequestBody OrderRequest orderRequest) throws ProductNotAvailableException,
            ProductEfficiencyException, RateExceedException, HolidayException, OfferNotAvailableException, OfferExpiredException, OfferNotApplicableException {

        OrderResponse savedOrder=orderServiceInterface.addOrder(orderRequest);
        return new ResponseEntity<OrderResponse>(savedOrder, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @RequestBody OrderRequest orderRequest) throws ProductNotAvailableException,
            ProductEfficiencyException, RateExceedException, HolidayException, OfferNotAvailableException, OfferExpiredException, OfferNotApplicableException {

        OrderResponse savedOrder=orderServiceInterface.addOrder(orderRequest);
        return new ResponseEntity<OrderResponse>(savedOrder, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrder(@RequestBody OrderRequest orderRequest)
    {
        List<OrderResponse> listOfAllOrders=orderServiceInterface.getAllOrder();
        return new ResponseEntity<List<OrderResponse>>(listOfAllOrders,HttpStatus.OK);
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponse> getOrderItemById(@PathVariable Long id,@RequestBody Order order)
    {
        OrderResponse orderResponse=orderServiceInterface.getOrderById(id);
        return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
    }

    @PostMapping("/saveitem")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem)
    {

        OrderItem savedOrderItem=orderServiceInterface.addOrderItem(orderItem);
        return new ResponseEntity<OrderItem>(savedOrderItem, HttpStatus.CREATED);

    }

    @GetMapping("/allitem")
    public ResponseEntity<List<OrderItemResponse>> getAllOrderItem(@RequestBody OrderItemRequest orderItemRequest)
    {
        List<OrderItemResponse> listOfAllOrderItem=orderServiceInterface.getAllOrderItem();
        return new ResponseEntity<List<OrderItemResponse>>(listOfAllOrderItem,HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<OrderItemResponse> getOrderItemById(@PathVariable Long id, @RequestBody OrderItemRequest orderItemRequest)
    {
        OrderItemResponse orderItemResponse=orderServiceInterface.getOrderItemById(id);
        return new ResponseEntity<OrderItemResponse>(orderItemResponse,HttpStatus.OK);
    }


}
