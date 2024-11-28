package com.example.springsecdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springsecdemo.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
