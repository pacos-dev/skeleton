package org.pacos.plugin.skeleton.backend;

import org.pacos.plugin.skeleton.backend.todo.ToDoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoProxyTest {

    @Test
    void whenGetToDoServiceCalledThenReturnProvidedService() {
        ToDoService mockToDoService = Mockito.mock(ToDoService.class);

        ToDoProxy toDoProxy = new ToDoProxy(mockToDoService);

        assertEquals(mockToDoService, toDoProxy.getToDoService());
    }
}