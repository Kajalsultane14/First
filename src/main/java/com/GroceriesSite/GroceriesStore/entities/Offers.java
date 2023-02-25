package com.GroceriesSite.GroceriesStore.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private BigDecimal discountInRs;

    private BigDecimal applicablePurchaseAmount;

    private LocalDate expiryDate;
}
