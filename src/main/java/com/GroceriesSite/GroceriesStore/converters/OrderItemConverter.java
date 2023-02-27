package com.GroceriesSite.GroceriesStore.converters;

import com.GroceriesSite.GroceriesStore.entities.OrderItem;
import com.GroceriesSite.GroceriesStore.models.OrderItemRequest;
import com.GroceriesSite.GroceriesStore.models.OrderItemResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {


    private final ObjectMapper mapper;

    public OrderItemResponse entityToDto(OrderItem orderItem)
    {

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(orderItem,OrderItemResponse.class);

    }

    public List<OrderItemResponse> entityToDto(List<OrderItem> orderItems)
    {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(orderItems,new TypeReference<List<OrderItemResponse>>(){});
    }

    public OrderItem dtoToEntity(OrderItemRequest orderItemRequest)
    {

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.convertValue(orderItemRequest,OrderItem.class);

    }

}
