package com.GroceriesSite.GroceriesStore.controller;

import com.GroceriesSite.GroceriesStore.helper.JwtUtil;
import com.GroceriesSite.GroceriesStore.model.JwtRequest;
import com.GroceriesSite.GroceriesStore.model.JwtResponse;
import com.GroceriesSite.GroceriesStore.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try
        {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (jwtRequest.getUsername(),jwtRequest.getPassword()));

        }
        catch(UsernameNotFoundException ex)
        {
            ex.printStackTrace();
            throw  new Exception("Bad Credentials");
        }
        catch(BadCredentialsException ex)
        {
            ex.printStackTrace();
            throw  new Exception("Bad Credentials");

        }

        UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT"+token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
