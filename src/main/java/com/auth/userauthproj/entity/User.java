package com.auth.userauthproj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table (name = "user")
public class User {
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column (unique = true, nullable = false)
    private String username;

    @Column (nullable = false)
    private String password;

    @ManyToMany (fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
