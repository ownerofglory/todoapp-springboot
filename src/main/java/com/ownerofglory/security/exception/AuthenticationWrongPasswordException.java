package com.ownerofglory.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationWrongPasswordException extends AuthenticationException {
    public AuthenticationWrongPasswordException() {
        super("Wrong password");
    }
}
