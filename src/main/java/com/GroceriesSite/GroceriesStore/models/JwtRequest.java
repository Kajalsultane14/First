package com.GroceriesSite.GroceriesStore.models;

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
