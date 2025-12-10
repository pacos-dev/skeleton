package org.pacos.plugin.skeleton.backend.todo;

import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service provides simple managing on the database object
 */
@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    /**
     * Save to-do as a new element on database
     * @return id stored element
     */
    public int saveNewToDo(ToDoDTO dto) {
        ToDo toDo = new ToDo();
        toDo.setName(dto.getName());
        toDo.setDone(dto.isActive());
        toDo.setUserId(dto.getUserId());
        toDo = toDoRepository.save(toDo);
        return toDo.getId();
    }

    /**
     * Loads all to-do belongs to user identified by ID
     */
    public List<ToDoDTO> loadForUser(int userId) {
        return ToDoMapper.mapList(toDoRepository.findAllByUserId(userId));
    }

    /**
     * Removes given to-do from database based on ID
     */
    public void removeToDo(ToDoDTO dto) {
        toDoRepository.deleteById(dto.getId());
    }

    /**
     * Update TO-DO item
     * @param dto
     */
    public void updateToDo(ToDoDTO dto) {
        ToDo toDo = toDoRepository.findById(dto.getId()).orElseThrow();
        toDo.setName(dto.getName());
        toDo.setDone(dto.isActive());
        toDoRepository.save(toDo);
    }
}
