package org.pacos.plugin.skeleton.backend.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoTest {

    @Test
    void whenSetAndGetIdCalledThenReturnCorrectId() {
        ToDo toDo = new ToDo();
        toDo.setUserId(1);
        assertEquals(1, toDo.getUserId());
    }

    @Test
    void whenSetAndGetNameCalledThenReturnCorrectName() {
        ToDo toDo = new ToDo();
        toDo.setName("Test Name");
        assertEquals("Test Name", toDo.getName());
    }

    @Test
    void whenSetAndGetDoneCalledThenReturnCorrectValue() {
        ToDo toDo = new ToDo();
        toDo.setDone(true);
        assertTrue(toDo.isDone());
    }

    @Test
    void whenEqualsCalledWithEqualObjectsThenReturnTrue() {
        ToDo toDo1 = new ToDo();
        toDo1.setDone(true);

        ToDo toDo2 = new ToDo();
        toDo2.setDone(true);
        assertEquals(toDo2.hashCode(), toDo1.hashCode());
    }
}
