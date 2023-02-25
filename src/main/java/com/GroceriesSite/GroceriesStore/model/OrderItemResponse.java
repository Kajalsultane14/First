package com.GroceriesSite.GroceriesStore.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Data
public class OrderItemResponse {

    private Long productId;
    @NotBlank(message = "can not be blank")
    private String productName;
    private BigDecimal rate;
    private Integer availableQuantity;
}
