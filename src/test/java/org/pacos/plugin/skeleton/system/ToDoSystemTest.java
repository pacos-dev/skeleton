package org.pacos.plugin.skeleton.system;

import org.pacos.base.session.UserDTO;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.view.PanelTodo;
import org.pacos.plugin.skeleton.view.VaadinMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ToDoSystemTest {

    @Test
    void whenGetProxyCalledThenReturnProvidedProxy() {
        PanelTodo mockPanel = mock(PanelTodo.class);
        ToDoProxy mockProxy = mock(ToDoProxy.class);

        ToDoSystem toDoSystem = new ToDoSystem(mockPanel, mockProxy);

        assertEquals(mockProxy, toDoSystem.getProxy());
    }

    @Test
    void whenGetPanelCalledThenReturnProvidedPanel() {
        PanelTodo mockPanel = mock(PanelTodo.class);
        ToDoProxy mockProxy = mock(ToDoProxy.class);

        ToDoSystem toDoSystem = new ToDoSystem(mockPanel, mockProxy);

        assertEquals(mockPanel, toDoSystem.getPanel());
    }

    @Test
    void whenGetUserIdThenReturnUserIdFromCurrentSession() {
        //given
        int userId = 10;
        VaadinMock.mockSystem(new UserDTO(userId, "test", 0));
        PanelTodo mockPanel = mock(PanelTodo.class);
        ToDoProxy mockProxy = mock(ToDoProxy.class);

        ToDoSystem toDoSystem = new ToDoSystem(mockPanel, mockProxy);
        //then
        assertEquals(userId, toDoSystem.getUserId());
    }
}
