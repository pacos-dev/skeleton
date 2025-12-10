package org.pacos.plugin.skeleton.backend.todo;

import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    private ToDoService toDoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        toDoService = new ToDoService(toDoRepository);
    }

    @Test
    void whenSaveNewToDoCalledThenReturnSavedId() {
        ToDoDTO dto = new ToDoDTO();
        dto.setName("Test ToDo");
        dto.setActive(true);
        dto.setUserId(1);

        ToDo toDo = new ToDo();
        toDo.setId(1);
        toDo.setName(dto.getName());
        toDo.setDone(dto.isActive());
        toDo.setUserId(dto.getUserId());

        when(toDoRepository.save(any(ToDo.class))).thenReturn(toDo);

        int savedId = toDoService.saveNewToDo(dto);

        assertEquals(1, savedId);
        verify(toDoRepository, times(1)).save(any(ToDo.class));
    }

    @Test
    void whenLoadForUserCalledThenReturnToDoList() {
        ToDoDTO dto1 = new ToDoDTO();
        dto1.setId(1);
        dto1.setName("ToDo 1");
        dto1.setActive(true);
        dto1.setUserId(1);

        ToDoDTO dto2 = new ToDoDTO();
        dto2.setId(2);
        dto2.setName("ToDo 2");
        dto2.setActive(false);
        dto2.setUserId(1);

        List<ToDo> toDoList = List.of(
                new ToDo() {{
                    setId(1);
                    setName(dto1.getName());
                    setDone(dto1.isActive());
                    setUserId(dto1.getUserId());
                }},
                new ToDo() {{
                    setId(2);
                    setName(dto2.getName());
                    setDone(dto2.isActive());
                    setUserId(dto2.getUserId());
                }}
        );

        when(toDoRepository.findAllByUserId(1)).thenReturn(toDoList);

        List<ToDoDTO> result = toDoService.loadForUser(1);

        assertEquals(2, result.size());
        assertEquals("ToDo 1", result.get(0).getName());
        assertEquals("ToDo 2", result.get(1).getName());
    }

    @Test
    void whenRemoveToDoCalledThenDeleteToDo() {
        ToDoDTO dto = new ToDoDTO();
        dto.setId(1);

        doNothing().when(toDoRepository).deleteById(1);

        toDoService.removeToDo(dto);

        verify(toDoRepository, times(1)).deleteById(1);
    }

    @Test
    void whenUpdateToDoCalledThenUpdateToDoInRepository() {
        ToDoDTO dto = new ToDoDTO();
        dto.setId(1);
        dto.setName("Updated ToDo");
        dto.setActive(true);

        ToDo existingToDo = new ToDo();
        existingToDo.setName("Old ToDo");
        existingToDo.setDone(false);
        existingToDo.setUserId(1);

        when(toDoRepository.findById(1)).thenReturn(java.util.Optional.of(existingToDo));
        when(toDoRepository.save(any(ToDo.class))).thenReturn(existingToDo);

        toDoService.updateToDo(dto);

        assertEquals("Updated ToDo", existingToDo.getName());
        assertTrue(existingToDo.isDone());
        verify(toDoRepository, times(1)).save(any(ToDo.class));
    }
}
