package com.ownerofglory.service.impl;

import com.ownerofglory.model.Todo;
import com.ownerofglory.model.dto.TodoDTO;
import com.ownerofglory.model.mapper.TodoMapper;
import com.ownerofglory.repository.TodoRepository;
import com.ownerofglory.service.TodoService;
import com.ownerofglory.service.exception.TodoDoesNotExistException;
import com.ownerofglory.service.exception.TodoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;
    private final TodoMapper mapper;

    @Override
    public List<TodoDTO> getAllTodos(Long userId) {
        List<Todo> allByUserId = repository.getAllByUserId(userId);
        return allByUserId.stream()
                .map(mapper::todoToTodoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO getTodo(Long id) throws TodoException {
        if (!repository.existsById(id)) {
            throw new TodoDoesNotExistException();
        }
        Todo one = repository.getOne(id);
        return mapper.todoToTodoDTO(one);
    }

    @Override
    public void addTodo(TodoDTO dto) {
        dto.setId(null);
        Todo todo = mapper.todoDTOToTodo(dto);
        repository.save(todo);
        repository.flush();
    }

    @Override
    public void updateTodo(TodoDTO dto)  throws TodoException {
        if (!repository.existsById(dto.getId())) {
            throw new TodoDoesNotExistException();
        }

        Todo todo = mapper.todoDTOToTodo(dto);
        repository.save(todo);
        repository.flush();
    }

    @Override
    public void deleteTodo(Long id) throws TodoException {
        if (!repository.existsById(id)) {
            throw new TodoDoesNotExistException();
        }
        repository.deleteById(id);
        repository.flush();
    }
}
