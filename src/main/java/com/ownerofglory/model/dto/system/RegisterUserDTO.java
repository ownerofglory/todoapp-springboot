package com.ownerofglory.model.dto.system;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String username;
    private String password;
    private String passwordRepeat;
}
