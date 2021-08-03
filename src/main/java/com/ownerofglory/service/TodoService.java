package com.ownerofglory.service;

import com.ownerofglory.model.dto.TodoDTO;
import com.ownerofglory.service.exception.TodoAlreadyExistException;
import com.ownerofglory.service.exception.TodoException;

import java.util.List;

public interface TodoService {
    List<TodoDTO> getAllTodos(Long userId);
    TodoDTO getTodo(Long id) throws TodoException;
    void addTodo(TodoDTO todo) throws TodoAlreadyExistException, TodoException;
    void updateTodo(TodoDTO todo) throws TodoException;
    void deleteTodo(Long id) throws TodoException;
}
