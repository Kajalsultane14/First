package com.GroceriesSite.GroceriesStore.controller;

import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exception.HolidayException;
import com.GroceriesSite.GroceriesStore.exception.ProductEfficiencyException;
import com.GroceriesSite.GroceriesStore.exception.ProductNotAvailableException;
import com.GroceriesSite.GroceriesStore.exception.RateExceedException;
import com.GroceriesSite.GroceriesStore.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/coder")
@Validated
public class OrderController {

    @Autowired
    private OrderServiceInterface orderServiceInterface;

    @PostMapping("/save")
    public ResponseEntity<Order> addOrder( @Valid @RequestBody  Order order) throws ProductNotAvailableException,
            ProductEfficiencyException, RateExceedException, HolidayException {

        Order savedOrder=orderServiceInterface.addOrder(order);
        return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order) throws ProductNotAvailableException,
            ProductEfficiencyException, RateExceedException, HolidayException {

        Order savedOrder=orderServiceInterface.addOrder(order);
        return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder(@RequestBody Order order)
    {
        List<Order> listOfAllOrders=orderServiceInterface.getAllOrder();
        return new ResponseEntity<List<Order>>(listOfAllOrders,HttpStatus.OK);
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderItemById(@PathVariable Long id,@RequestBody Order order)
    {
        Order Order=orderServiceInterface.getOrderById(id);
        return new ResponseEntity<Order>(Order,HttpStatus.OK);
    }

    @PostMapping("/saveitem")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem)
    {

        OrderItem savedOrderItem=orderServiceInterface.addOrderItem(orderItem);
        return new ResponseEntity<OrderItem>(savedOrderItem, HttpStatus.CREATED);

    }

    @GetMapping("/allitem")
    public ResponseEntity<List<OrderItem>> getAllOrderItem(@RequestBody OrderItem orderItem)
    {
        List<OrderItem> listOfAllOrderItem=orderServiceInterface.getAllOrderItem();
        return new ResponseEntity<List<OrderItem>>(listOfAllOrderItem,HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id,@RequestBody OrderItem orderItem)
    {
        OrderItem OrderItem=orderServiceInterface.getOrderItemById(id);
        return new ResponseEntity<OrderItem>(OrderItem,HttpStatus.OK);
    }


}
