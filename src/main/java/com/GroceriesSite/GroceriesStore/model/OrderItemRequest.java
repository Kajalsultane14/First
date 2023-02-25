package com.GroceriesSite.GroceriesStore.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OrderItemRequest {

    private Long productId;
    private String productName;
    private BigDecimal rate;
    private Integer availableQuantity;


}
