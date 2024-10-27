package com.codingshuttle.project.uber.uberApplication.entities;

import com.codingshuttle.project.uber.uberApplication.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY) //creates a seperate table as the field is a collection
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


}
