package com.excercise.ecommerce.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String username;
    private String password;
}
