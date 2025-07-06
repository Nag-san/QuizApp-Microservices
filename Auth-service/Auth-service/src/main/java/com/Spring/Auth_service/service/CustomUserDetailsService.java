package com.Spring.Auth_service.service;

import com.Spring.Auth_service.config.CustomUserDetails;
import com.Spring.Auth_service.model.UserCredential;
import com.Spring.Auth_service.repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential user = repo.findByName(username);
        if(user!=null) {
            UserDetails userDetails = new CustomUserDetails(user);
            System.out.println("User name: " + userDetails.getUsername());
            System.out.println("User password: " + userDetails.getPassword());
            return userDetails;
        }
        else throw new UsernameNotFoundException("Username not found");
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserCredential user = repo.findByName(username);
//        if (user != null) {
//            return new org.springframework.security.core.userdetails.User(
//                    user.getName(),               // username
//                    user.getPassword(),           // hashed password
//                    new ArrayList<>()             // authorities (empty for now)
//            );
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
}
