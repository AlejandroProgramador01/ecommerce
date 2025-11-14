package com.excercise.ecommerce.user.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("El correo electrónico '" + email + "' ya está registrado");
    }
}

