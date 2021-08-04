package com.ownerofglory.model.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
public class UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean isEnabled;
    private Set<? extends GrantedAuthority> authorities;

}
