package org.pacos.plugin.skeleton.backend.todo;

import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class ToDoMapperTest {

    @Test
    void whenMapCalledThenReturnCorrectDTO() {
        ToDo toDo = spy(new ToDo());
        doReturn(1).when(toDo).getId();
        toDo.setDone(true);
        toDo.setName("Test Name");
        toDo.setUserId(1);

        ToDoDTO dto = ToDoMapper.map(toDo);

        assertEquals(1, dto.getId());
        assertEquals("Test Name", dto.getName());
        assertTrue(dto.isActive());
        assertEquals(1, dto.getUserId());
    }

    @Test
    void whenMapListCalledThenReturnCorrectDTOList() {
        ToDo toDo1 = spy(new ToDo());
        doReturn(1).when(toDo1).getId();
        toDo1.setDone(true);
        toDo1.setName("Task 1");
        toDo1.setUserId(1);


        ToDo toDo2 = Mockito.spy(new ToDo());
        doReturn(2).when(toDo2).getId();
        toDo2.setDone(false);
        toDo2.setName("Task 2");
        toDo2.setUserId(2);

        List<ToDo> toDoList = List.of(toDo1, toDo2);

        List<ToDoDTO> dtoList = ToDoMapper.mapList(toDoList);

        assertEquals(2, dtoList.size());

        ToDoDTO dto1 = dtoList.get(0);
        assertEquals("Task 1", dto1.getName());
        assertTrue(dto1.isActive());
        assertEquals(1, dto1.getUserId());

        ToDoDTO dto2 = dtoList.get(1);
        assertEquals("Task 2", dto2.getName());
        assertFalse(dto2.isActive());
        assertEquals(2, dto2.getUserId());
    }
}
