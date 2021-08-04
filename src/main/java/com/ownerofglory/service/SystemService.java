package com.ownerofglory.service;

import com.ownerofglory.model.User;
import com.ownerofglory.model.UserVerification;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.model.dto.system.LoginUserDTO;
import com.ownerofglory.model.dto.system.RegisterUserDTO;
import com.ownerofglory.model.dto.system.VerificationUserDTO;
import com.ownerofglory.service.exception.UserDoesNotExistException;

public interface SystemService {
    void registerUser(RegisterUserDTO user);
    void createVerificationToken(UserDTO user, String token);
    UserVerification getVerificationToken(String token);
    void confirmRegistration(String username) throws UserDoesNotExistException;
}
