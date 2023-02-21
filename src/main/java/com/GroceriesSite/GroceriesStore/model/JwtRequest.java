package com.GroceriesSite.GroceriesStore.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JwtRequest {

    String username;
    String password;


}
