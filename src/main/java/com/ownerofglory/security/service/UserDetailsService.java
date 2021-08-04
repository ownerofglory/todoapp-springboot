package com.ownerofglory.security.service;

import com.ownerofglory.model.User;
import com.ownerofglory.model.auth.UserDetails;
import com.ownerofglory.model.mapper.UserMapper;
import com.ownerofglory.repository.UserRepository;
import com.ownerofglory.service.exception.UserDoesNotExistException;
import com.ownerofglory.service.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDetails getUserDetailsByUsername(String username) throws UserException {
        Optional<User> userByUsername = repository.getUserByUsername(username);
        User user = userByUsername.orElseThrow(() -> new UserDoesNotExistException(username));
        return mapper.userToUserDetails(user);
    }
}
