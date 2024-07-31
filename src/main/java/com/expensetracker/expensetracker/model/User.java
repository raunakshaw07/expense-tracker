package com.expensetracker.expensetracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}

