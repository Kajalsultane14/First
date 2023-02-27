package com.GroceriesSite.GroceriesStore.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private Long productId;
    @NotNull(message = "product quantity can not be null")
    private Long productQuantity;

    @NotBlank(message="Product name should not be blank")
    private String productName;
    @NotNull(message = "date can not be null")
    private LocalDate orderDate;

    private Long offerId;

    private BigDecimal orderAmount;
}
