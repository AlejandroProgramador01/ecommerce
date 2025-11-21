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
    @GeneratedValue
    private Long id;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<QualificationEntity> qualifications;
}
