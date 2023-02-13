package com.GroceriesSite.GroceriesStore.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="demand")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @NotNull(message = "id can not be null")
    private Long productId;
    @NotNull(message = "product quantity can not be null")
    private Long productQuantity;

    @NotBlank(message="Product name should not be blank")
    private String productName;
    @NotNull(message = "date can not be null")
    private LocalDate orderDate;



}
