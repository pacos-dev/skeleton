package org.pacos.plugin.skeleton.view;

import org.pacos.base.session.UserDTO;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.backend.ToDoVariableProvider;
import org.pacos.plugin.skeleton.backend.todo.ToDoService;
import org.pacos.plugin.skeleton.view.config.MyTodoConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

class PanelTodoTest {

    @Test
    void whenInitializePanelThenNoExceptions() {
        //given
        //mock user session
        UserDTO user = new UserDTO(1, "test", 0);
        VaadinMock.mockSystem(user);
        //mock services
        MyTodoConfig myTodoConfig = Mockito.mock(MyTodoConfig.class);
        ToDoProxy toDoProxy = Mockito.mock(ToDoProxy.class);
        ToDoService toDoService = Mockito.mock(ToDoService.class);
        ToDoVariableProvider variableProvider = Mockito.mock(ToDoVariableProvider.class);
        when(toDoProxy.getToDoService()).thenReturn(toDoService);
        when(toDoService.loadForUser(user.getId())).thenReturn(List.of());
        //then
        assertDoesNotThrow(() -> new PanelTodo(myTodoConfig, toDoProxy));
    }
}