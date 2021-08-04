package com.ownerofglory.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String title;
    private boolean isDone;

    @ManyToOne
    private User user;
}
