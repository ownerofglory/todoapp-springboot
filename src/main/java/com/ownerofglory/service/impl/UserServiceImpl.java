package com.ownerofglory.service.impl;

import com.ownerofglory.model.User;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.model.mapper.UserMapper;
import com.ownerofglory.repository.UserRepository;
import com.ownerofglory.service.UserService;
import com.ownerofglory.service.exception.UserDoesNotExistException;
import com.ownerofglory.service.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDTO getUser(Long id) {
        User user = repository.getOne(id);
        return mapper.userToUserDTO(user);
    }

    @Override
    public UserDTO getUserByUserName(String username) throws UserException {
        Optional<User> userByUsername = repository.getUserByUsername(username);
        User user = userByUsername.orElseThrow(UserDoesNotExistException::new);
        return mapper.userToUserDTO(user);
    }
}
