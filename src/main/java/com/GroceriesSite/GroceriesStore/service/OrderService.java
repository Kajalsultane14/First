package com.GroceriesSite.GroceriesStore.service;

import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exception.HolidayException;
import com.GroceriesSite.GroceriesStore.exception.ProductEfficiencyException;
import com.GroceriesSite.GroceriesStore.exception.ProductNotAvailableException;
import com.GroceriesSite.GroceriesStore.exception.RateExceedException;
import com.GroceriesSite.GroceriesStore.repose.OrderItemRepository;
import com.GroceriesSite.GroceriesStore.repose.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;
    Long totalRate=0l;
    @Override
    public Order addOrder(Order order) throws ProductNotAvailableException,
            ProductEfficiencyException, RateExceedException, HolidayException {


        Optional product=orderItemRepository.findById(order.getProductId());

        if(order.getOrderDate().getDayOfWeek()== DayOfWeek.SUNDAY)
        {
            //checking if the order date is sunday
            throw new HolidayException("can not take orders on sunday");

        }
        if(product.isEmpty())
        {
            //checking if product is available or not
            throw new ProductNotAvailableException("sorry product is not available");
        }
        else {

            OrderItem orderItem= getOrderItemById(order.getProductId());
            totalRate=totalRate+(orderItem.getRate()*order.getProductQuantity());

            if(orderItem.getAvailableQuantity()<order.getProductQuantity())
            {
                //checking if demand of product is higher than availability
                String msg="Sorry only "+orderItem.getAvailableQuantity()+"  "+order.getProductName()+" available";
                throw new ProductEfficiencyException(msg);
            }
            else if(!(totalRate>99&&totalRate<4999))
            {
                //if total rate is not in between 99rs to 4999rs then throwing an exception
                totalRate=0l;
                throw  new RateExceedException("Rate exceeds limits");

            }
            else {

                Order savedOrder = orderRepository.save(order);
                return savedOrder;
            }
        }



    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id).get();
    }



    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(Long id) {

        return orderItemRepository.findById(id).get();
    }
    public OrderItem addOrderItem(OrderItem orderItem)
    {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return savedOrderItem;
    }
}