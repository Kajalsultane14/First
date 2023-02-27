package com.GroceriesSite.GroceriesStore.converters;

import com.GroceriesSite.GroceriesStore.entities.Order;
import com.GroceriesSite.GroceriesStore.models.OrderRequest;
import com.GroceriesSite.GroceriesStore.models.OrderResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final ObjectMapper mapper;

    public OrderResponse entityToDto(Order order)
    {

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(order,OrderResponse.class);

    }

    public List<OrderResponse> entityToDto(List<Order> orders)
    {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(orders,new TypeReference<List<OrderResponse>>(){});
    }

    public Order dtoToEntity(OrderResponse orderResponse)
    {

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(orderResponse,Order.class);

    }

    public OrderResponse dtoToDto(OrderRequest orderRequest)
    {

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(orderRequest,OrderResponse.class);

    }



}
