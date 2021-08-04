package com.ownerofglory.controller;

import com.ownerofglory.configuration.ApplicationConstants;
import com.ownerofglory.configuration.ApplicationProperties;
import com.ownerofglory.events.OnRegistrationCompleteEvent;
import com.ownerofglory.model.UserPrincipal;
import com.ownerofglory.model.UserVerification;
import com.ownerofglory.model.auth.UserDetails;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.model.dto.system.LoginUserDTO;
import com.ownerofglory.model.dto.system.RegisterUserDTO;
import com.ownerofglory.model.mapper.UserMapper;
import com.ownerofglory.security.jwt.JwtTokenProvider;
import com.ownerofglory.security.util.ResponseUtils;
import com.ownerofglory.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class SystemController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationProperties appProperties;
    private final SystemService systemService;
    private final ApplicationEventPublisher eventPublisher;
    private final UserMapper userMapper;


    @PostMapping(value = "/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> authenticate(@RequestBody LoginUserDTO loginUser) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        User user = new User(userPrincipal.getUsername(), "", userPrincipal.getAuthorities());
        String userJwt = jwtTokenProvider.createUserJwt(userPrincipal.getId().toString(), user);
        HttpHeaders headerWithJwt = ResponseUtils.createHeaderWithJwt(appProperties.getAuth(), userJwt);

        return ResponseEntity.ok().headers(headerWithJwt).build();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody RegisterUserDTO registerUser) {
        systemService.registerUser(registerUser);
        UserDTO userDTO = userMapper.registerUserDTOToUserDTO(registerUser);

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userDTO, Locale.GERMANY, ""));

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/confirm")
    public ResponseEntity<Void> confirm(@RequestParam("token") String token) {
        try {
            UserVerification verificationToken = systemService.getVerificationToken(token);

            String username = verificationToken.getUser().getUsername();
            systemService.confirmRegistration(username);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
