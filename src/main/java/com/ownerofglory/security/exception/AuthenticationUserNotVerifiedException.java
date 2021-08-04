package com.ownerofglory.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationUserNotVerifiedException extends AuthenticationException {
    public AuthenticationUserNotVerifiedException(String msg) {
        super(msg);
    }
}
