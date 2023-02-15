package com.GroceriesSite.GroceriesStore.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name="item")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotBlank(message = "can not be blank")
    private String productName;
    private Long rate;
    private Integer availableQuantity;



}
