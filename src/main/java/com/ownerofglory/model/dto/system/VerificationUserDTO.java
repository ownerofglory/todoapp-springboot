package com.ownerofglory.model.dto.system;

import com.ownerofglory.model.dto.UserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class VerificationUserDTO {
    private Long id;
    private String token;
    private Date expiryDate;
    private UserDTO user;
}
