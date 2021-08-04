package com.ownerofglory.service.exception;

public class UserDoesNotExistException extends UserException {
    public UserDoesNotExistException(String username) {
        super(String.format("User with username '%s' does not exist", username));
    }
}
