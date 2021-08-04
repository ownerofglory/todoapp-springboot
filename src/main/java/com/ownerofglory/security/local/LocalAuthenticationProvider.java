package com.ownerofglory.security.local;

import com.ownerofglory.model.User;
import com.ownerofglory.model.auth.UserDetails;
import com.ownerofglory.security.exception.AuthenticationUserNorFoundException;
import com.ownerofglory.security.exception.AuthenticationUserNotVerifiedException;
import com.ownerofglory.security.exception.AuthenticationWrongPasswordException;
import com.ownerofglory.security.service.UserDetailsService;
import com.ownerofglory.service.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = (String) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();
            UserDetails userDetails = userDetailsService.getUserDetailsByUsername(username);

            if (!userDetails.isEnabled()) {
                throw new AuthenticationUserNotVerifiedException("User is not verified");
            }

            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new AuthenticationWrongPasswordException();
            }

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        } catch (UserException e) {
            throw new AuthenticationUserNorFoundException("Auth failed: user not found", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
