package com.ownerofglory.service;

import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.service.exception.UserException;

public interface UserService {
    UserDTO getUser(Long id);
    UserDTO getUserByUserName(String username) throws UserException;
}
