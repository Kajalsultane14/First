package com.GroceriesSite.GroceriesStore.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@Table(name="demand")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull

    private Long productId;
    @NonNull
    private Long productQuantity;
    @NonNull
    private String productName;
    @NonNull
    private LocalDate orderDate;



}
