package com.GroceriesSite.GroceriesStore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {


    private Long productId;
    @NotNull(message = "product quantity can not be null")
    private Long productQuantity;

    @NotBlank(message="Product name should not be blank")
    private String productName;
    @NotNull(message = "date can not be null")
    private LocalDate orderDate;

    private Long offerId;
}
