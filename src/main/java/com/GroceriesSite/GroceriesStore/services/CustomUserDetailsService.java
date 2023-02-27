package com.GroceriesSite.GroceriesStore.services;

import com.GroceriesSite.GroceriesStore.repositories.UserRepository;
import com.GroceriesSite.GroceriesStore.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

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
