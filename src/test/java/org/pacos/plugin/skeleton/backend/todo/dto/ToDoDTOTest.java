package org.pacos.plugin.skeleton.backend.todo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoDTOTest {

    @Test
    void whenSetAndGetIdCalledThenReturnCorrectId() {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(1);
        assertEquals(1, toDoDTO.getId());
    }

    @Test
    void whenSetAndGetNameCalledThenReturnCorrectName() {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setName("Test Name");
        assertEquals("Test Name", toDoDTO.getName());
    }

    @Test
    void whenSetAndGetActiveCalledThenReturnCorrectValue() {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setActive(true);
        assertTrue(toDoDTO.isActive());
    }

    @Test
    void whenSetAndGetUserIdCalledThenReturnCorrectUserId() {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setUserId(100);
        assertEquals(100, toDoDTO.getUserId());
    }

    @Test
    void whenEqualsCalledWithEqualObjectsThenReturnTrue() {
        ToDoDTO toDoDTO1 = new ToDoDTO();
        toDoDTO1.setId(1);

        ToDoDTO toDoDTO2 = new ToDoDTO();
        toDoDTO2.setId(1);

        assertEquals(toDoDTO1, toDoDTO2);
    }

    @Test
    void whenEqualsCalledWithDifferentObjectsThenReturnFalse() {
        ToDoDTO toDoDTO1 = new ToDoDTO();
        toDoDTO1.setId(1);

        ToDoDTO toDoDTO2 = new ToDoDTO();
        toDoDTO2.setId(2);

        assertNotEquals(toDoDTO1, toDoDTO2);
    }

    @Test
    void whenHashCodeCalledOnEqualObjectsThenReturnSameHashCode() {
        ToDoDTO toDoDTO1 = new ToDoDTO();
        toDoDTO1.setId(1);

        ToDoDTO toDoDTO2 = new ToDoDTO();
        toDoDTO2.setId(1);

        assertEquals(toDoDTO1.hashCode(), toDoDTO2.hashCode());
    }

    @Test
    void whenHashCodeCalledOnDifferentObjectsThenReturnDifferentHashCodes() {
        ToDoDTO toDoDTO1 = new ToDoDTO();
        toDoDTO1.setId(1);

        ToDoDTO toDoDTO2 = new ToDoDTO();
        toDoDTO2.setId(2);

        assertNotEquals(toDoDTO1.hashCode(), toDoDTO2.hashCode());
    }
}
