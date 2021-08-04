package com.ownerofglory.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String authority;
    private List<TodoDTO> todos;
}
