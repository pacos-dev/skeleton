package org.pacos.plugin.skeleton.system.event;

import com.vaadin.flow.component.notification.Notification;
import org.pacos.base.session.UserDTO;
import org.pacos.base.window.config.impl.ConfirmationWindowConfig;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.backend.todo.ToDoService;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoEvent;
import org.pacos.plugin.skeleton.system.ToDoSystem;
import org.pacos.plugin.skeleton.view.PanelTodo;
import org.pacos.plugin.skeleton.view.VaadinMock;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class RemoveToDoEventTest {

    @Test
    void whenRemoveEventAndNoPermissionsThenDoNothing() {
        //given
        VaadinMock.mockSystem(new UserDTO("guest"));
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getPanel()).thenReturn(Mockito.mock(PanelTodo.class));
        ToDoDTO dto = new ToDoDTO();
        //WHEN
        RemoveToDoEvent.fireEvent(toDoSystem,dto);

        //then
        Mockito.verifyNoInteractions(toDoSystem.getPanel());
    }

    @Test
    void whenRemoveEventThenShowConfirmationWindow() {
        VaadinMock.mockSystem(new UserDTO("guest"), ToDoPermissions.TODO_ADD);
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getPanel()).thenReturn(Mockito.mock(PanelTodo.class));
        ToDoDTO dto = new ToDoDTO();
        //when
        RemoveToDoEvent.fireEvent(toDoSystem, dto);
        //then
        Mockito.verify(toDoSystem.getPanel()).addWindow(any(ConfirmationWindowConfig.class));
    }

    @Test
    void whenConfirmBtnClickedThenRemoveTodoItem() {
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getProxy()).thenReturn(Mockito.mock(ToDoProxy.class));
        Mockito.when(toDoSystem.getProxy().getToDoService()).thenReturn(Mockito.mock(ToDoService.class));
        ToDoDTO dto = new ToDoDTO();
        //when
        try (MockedStatic<Notification> notificationMock = mockStatic(Notification.class)) {
            Notification notify = Mockito.mock(Notification.class);
            notificationMock.when(() -> Notification.show(any())).thenReturn(notify);

            RemoveToDoEvent.onConfirm(toDoSystem, dto);
        }
        //then
        Mockito.verify(toDoSystem.getProxy().getToDoService()).removeToDo(dto);
    }

    @Test
    void whenConfirmBtnClickedThenNotifySystemItemWasRemoved() {
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getProxy()).thenReturn(Mockito.mock(ToDoProxy.class));
        Mockito.when(toDoSystem.getProxy().getToDoService()).thenReturn(Mockito.mock(ToDoService.class));
        ToDoDTO dto = new ToDoDTO();
        //when
        try (MockedStatic<Notification> notificationMock = mockStatic(Notification.class)) {
            Notification notify = Mockito.mock(Notification.class);
            notificationMock.when(() -> Notification.show(any())).thenReturn(notify);

            RemoveToDoEvent.onConfirm(toDoSystem, dto);
        }
        //then
        Mockito.verify(toDoSystem).notify(ToDoEvent.ITEM_TODO_REMOVED,dto);
    }
}