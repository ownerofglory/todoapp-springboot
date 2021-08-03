package com.ownerofglory.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_verification")
@Data
public class UserVerification {
    @Id
    private Long id;
    private Date expiryDate;
    private String token;
    @ManyToOne
    private User user;
}
