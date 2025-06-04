package com.example.JobApp.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

}
