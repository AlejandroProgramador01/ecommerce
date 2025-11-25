package com.excercise.ecommerce.user.entity;

import com.excercise.ecommerce.product.entity.QualificationEntity;
import com.excercise.ecommerce.user.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<QualificationEntity> qualifications;
}
