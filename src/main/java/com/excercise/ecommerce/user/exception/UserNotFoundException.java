package com.excercise.ecommerce.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Usuario con id " + id + " no encontrado");
    }

    public UserNotFoundException(String email) {
        super("Usuario con email " + email + " no encontrado");
    }
}


