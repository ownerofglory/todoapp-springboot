package com.ownerofglory.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserPrincipal implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    private boolean isEnabled;
}
