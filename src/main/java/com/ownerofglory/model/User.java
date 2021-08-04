package com.ownerofglory.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String authority;
    private boolean isEnabled;

    @OneToMany
    private List<Todo> todos;
    @OneToOne(targetEntity = UserVerification.class, fetch = FetchType.LAZY)
    private UserVerification userVerification;
}
