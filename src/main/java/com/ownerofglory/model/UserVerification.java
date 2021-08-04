package com.ownerofglory.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date expiryDate;
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;
}
