package com.ownerofglory.model.dto;

import lombok.Data;

@Data
public class TodoDTO {
    private Long id;
    private String title;
    private boolean isDone;
    private UserDTO user;
}
