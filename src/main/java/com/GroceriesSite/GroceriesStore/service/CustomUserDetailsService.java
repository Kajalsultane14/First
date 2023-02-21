package com.GroceriesSite.GroceriesStore.service;

//import com.GroceriesSite.GroceriesStore.entities.User;
import com.GroceriesSite.GroceriesStore.repose.OrderRepository;
import com.GroceriesSite.GroceriesStore.repose.UserRepository;
import com.GroceriesSite.GroceriesStore.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

     @Autowired
      UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Users users=userRepository.findByUsername(username);

        if(users!=null)
        {


            return new User(users.getUsername(),users.getPassword(),new ArrayList<>());

        }
        else
        {
            throw new UsernameNotFoundException(username+" does not exist");
        }



    }
}
