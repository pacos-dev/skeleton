package org.pacos.plugin.skeleton.backend.todo;

import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ToDoMapper {

    private ToDoMapper(){

    }
    public static ToDoDTO map(ToDo toDo) {
        ToDoDTO dto = new ToDoDTO();
        dto.setActive(toDo.isDone());
        dto.setName(toDo.getName());
        dto.setUserId(toDo.getUserId());
        dto.setId(toDo.getId());
        return dto;
    }

    public static List<ToDoDTO> mapList(List<ToDo> toDoList) {
        return toDoList.stream().map(ToDoMapper::map).collect(Collectors.toList());
    }
}
