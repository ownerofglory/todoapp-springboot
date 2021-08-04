package com.ownerofglory.service.impl;

import com.ownerofglory.model.User;
import com.ownerofglory.model.UserVerification;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.model.dto.system.RegisterUserDTO;
import com.ownerofglory.model.mapper.UserMapper;
import com.ownerofglory.repository.UserRepository;
import com.ownerofglory.repository.VerificationRepository;
import com.ownerofglory.service.SystemService;
import com.ownerofglory.service.exception.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        User user = mapper.registerUserDTOToUser(registerUserDTO);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAuthority("USER");
        userRepository.save(user);
        userRepository.flush();
    }

    @Override
    public void createVerificationToken(UserDTO userDto, String token) {
        UserVerification userVerification = new UserVerification();
        User user = mapper.userDTOToUser(userDto);
        userVerification.setToken(token);
        LocalDate now = LocalDate.now();
        LocalDate expiryDate = now.plusDays(1);
        userVerification.setExpiryDate(Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Optional<User> userByUsername = userRepository.getUserByUsername(user.getUsername());
        User userFromDb = userByUsername.orElseThrow();
        userVerification.setUser(userFromDb);

        verificationRepository.save(userVerification);
        verificationRepository.flush();
    }

    @Override
    public UserVerification getVerificationToken(String token) {
        Optional<UserVerification> byToken = verificationRepository.findByToken(token);
        return byToken.orElseThrow();
    }

    @Override
    public void confirmRegistration(String username) throws UserDoesNotExistException {
        Optional<User> userByUsername = userRepository.getUserByUsername(username);
        User user = userByUsername.orElseThrow(() -> new UserDoesNotExistException(username));
        user.setEnabled(true);
        userRepository.save(user);
        userRepository.flush();
    }
}
