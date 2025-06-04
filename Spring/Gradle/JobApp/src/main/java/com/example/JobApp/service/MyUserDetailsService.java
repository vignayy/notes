package com.example.JobApp.service;

import com.example.JobApp.model.User;
import com.example.JobApp.model.UserPrincipal;
import com.example.JobApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username);

        if (user == null) {
            System.out.println("No user with this User Name: " + username);
            throw new UsernameNotFoundException("User Not Found");
        } else {
            return new UserPrincipal(user);
        }
    }
}
