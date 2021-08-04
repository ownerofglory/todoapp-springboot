package com.ownerofglory.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationUserNorFoundException extends AuthenticationException {
    public AuthenticationUserNorFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
