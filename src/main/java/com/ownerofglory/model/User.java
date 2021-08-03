package com.ownerofglory.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;

    @OneToMany
    private List<Todo> todos;
    @OneToMany
    private List<UserVerification> verifications;
}
