package com.ownerofglory.model.mapper;

import com.ownerofglory.model.Todo;
import com.ownerofglory.model.dto.TodoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    TodoDTO todoToTodoDTO(Todo todo);
    Todo todoDTOToTodo(TodoDTO dto);
}
