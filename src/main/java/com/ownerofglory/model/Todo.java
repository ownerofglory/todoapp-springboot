package com.ownerofglory.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_todo")
@Data
public class Todo {
    @Id
    private Long id;
    private  String title;
    private boolean isDone;

    @ManyToOne
    private User user;
}
