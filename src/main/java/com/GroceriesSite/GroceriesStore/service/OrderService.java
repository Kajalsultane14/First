package com.GroceriesSite.GroceriesStore.service;

import com.GroceriesSite.GroceriesStore.entities.Offers;
import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.exception.*;
import com.GroceriesSite.GroceriesStore.model.OrderItemResponse;
import com.GroceriesSite.GroceriesStore.model.OrderRequest;
import com.GroceriesSite.GroceriesStore.model.OrderResponse;
import com.GroceriesSite.GroceriesStore.repose.OffersRepository;
import com.GroceriesSite.GroceriesStore.repose.OrderItemRepository;
import com.GroceriesSite.GroceriesStore.repose.OrderRepository;
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

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


                OrderResponse orderResponse= mapper.convertValue(orderRequest, OrderResponse.class);
                orderResponse.setOrderAmount(orderItemResponse.getRate().subtract(offers.getDiscountInRs()));

                Order savedOrders = orderRepository.save(mapper.convertValue(orderResponse, Order.class));
                return orderResponse;


            }
        }

    }

    @Override
    public List<OrderResponse> getAllOrder() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<Order> orders=orderRepository.findAll();

        return mapper.convertValue(orders,new TypeReference<List<OrderResponse>>(){});
    }

    @Override
    public OrderResponse getOrderById(Long id) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Order order=orderRepository.findById(id).get();
        return mapper.convertValue(order, OrderResponse.class);
    }

    @Override
    public List<OrderItemResponse> getAllOrderItem() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<OrderItem> orderItems=orderItemRepository.findAll();

        return mapper.convertValue(orderItems,new TypeReference<List<OrderItemResponse>>(){});
    }


    @Override
    public OrderItemResponse getOrderItemById(Long id) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        OrderItem orderItem=orderItemRepository.findById(id).get();
        return mapper.convertValue(orderItem, OrderItemResponse.class);
    }
    public OrderItem addOrderItem(OrderItem orderItem)
    {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return savedOrderItem;
    }
}