package com.GroceriesSite.GroceriesStore.services;

import com.GroceriesSite.GroceriesStore.converters.OrderConverter;
import com.GroceriesSite.GroceriesStore.converters.OrderItemConverter;
import com.GroceriesSite.GroceriesStore.entities.Offers;
import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exceptions.*;
import com.GroceriesSite.GroceriesStore.models.OrderItemRequest;
import com.GroceriesSite.GroceriesStore.models.OrderItemResponse;
import com.GroceriesSite.GroceriesStore.models.OrderRequest;
import com.GroceriesSite.GroceriesStore.models.OrderResponse;
import com.GroceriesSite.GroceriesStore.repositories.OffersRepository;
import com.GroceriesSite.GroceriesStore.repositories.OrderItemRepository;
import com.GroceriesSite.GroceriesStore.repositories.OrderRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceImpl {


    @Autowired
    OrderConverter orderConverter;

    @Autowired
    OrderItemConverter orderItemConverter;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OffersRepository offersRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) throws ProductNotAvailableException,
            ProductEfficiencyException, RateExceedException, HolidayException, OfferExpiredException, OfferNotApplicableException, OfferNotAvailableException {


        Optional product=orderItemRepository.findById(orderRequest.getProductId());
        Optional offer=offersRepository.findById(orderRequest.getOfferId());

        if(orderRequest.getOrderDate().getDayOfWeek()== DayOfWeek.SUNDAY)
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

            if (offer.isEmpty()) {

                throw new OfferNotAvailableException("No Offers Available");
            }

            OrderItemResponse orderItemResponse= getOrderItemById(orderRequest.getProductId());

            if(orderItemResponse.getAvailableQuantity()<orderRequest.getProductQuantity())
            {
                //checking if demand of product is higher than availability
                String msg="Sorry only "+orderItemResponse.getAvailableQuantity()+"  "+orderRequest.getProductName()+" available";

                throw new ProductEfficiencyException(msg);
            }
            else if((orderItemResponse.getRate().compareTo(BigDecimal.valueOf(99))==-1)
                    ||(orderItemResponse.getRate().compareTo(BigDecimal.valueOf(4999))==1))
            {

                //if total rate is not in between 99rs to 4999rs then throwing an exception
                throw  new RateExceedException("Rate exceeds limits");

            }
            else {


               Offers offers=offersRepository.findById(orderRequest.getOfferId()).get();

                    if (orderRequest.getOrderDate().isAfter(offers.getExpiryDate())) {
                        //checking for expiry date of offer
                        throw new OfferExpiredException("Offer has been expired");
                    }
                    if (!((orderItemResponse.getRate().compareTo(offers.getApplicablePurchaseAmount()) == 1)
                            || (orderItemResponse.getRate().compareTo(offers.getApplicablePurchaseAmount()) == 0))) {

                        //checking if offer is applicable on purchase amount
                        throw new OfferNotApplicableException("Offer Not Applicable");
                    }


                OrderResponse orderResponse= orderConverter.dtoToDto(orderRequest);
                orderResponse.setOrderAmount(orderItemResponse.getRate().subtract(offers.getDiscountInRs()));

                Order savedOrders = orderRepository.save(orderConverter.dtoToEntity(orderResponse));
                return orderResponse;


            }
        }

    }

    @Override
    public List<OrderResponse> getAllOrder() {

        List<Order> orders=orderRepository.findAll();
        return orderConverter.entityToDto(orders);
    }

    @Override
    public OrderResponse getOrderById(Long id) {

        Order order=orderRepository.findById(id).get();
        return orderConverter.entityToDto(order);
    }

    @Override
    public List<OrderItemResponse> getAllOrderItem() {

        List<OrderItem> orderItems=orderItemRepository.findAll();
        return orderItemConverter.entityToDto(orderItems);
    }


    @Override
    public OrderItemResponse getOrderItemById(Long id) {

        OrderItem orderItem=orderItemRepository.findById(id).get();
        return orderItemConverter.entityToDto(orderItem);
    }
    public OrderItemResponse addOrderItem(OrderItemRequest orderItemRequest)
    {
        OrderItem savedOrderItem = orderItemRepository.save(orderItemConverter.dtoToEntity(orderItemRequest));
        return orderItemConverter.entityToDto(savedOrderItem);
    }
}