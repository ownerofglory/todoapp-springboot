package com.ownerofglory.service;

import com.ownerofglory.model.User;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.service.exception.UserException;

public interface UserService {
    UserDTO getUser(Long id);
    User getUserByUserName(String username) throws UserException;
}
