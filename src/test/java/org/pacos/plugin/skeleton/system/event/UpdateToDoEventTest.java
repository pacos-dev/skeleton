package org.pacos.plugin.skeleton.system.event;

import jakarta.persistence.OptimisticLockException;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.pacos.base.session.UserDTO;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.backend.todo.ToDoService;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoSystem;
import org.pacos.plugin.skeleton.view.VaadinMock;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;

class UpdateToDoEventTest {

    @Test
    void whenNoPermissionThenDoNothing() {
        VaadinMock.mockSystem(new UserDTO("guest"));
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getProxy()).thenReturn(Mockito.mock(ToDoProxy.class));
        Mockito.when(toDoSystem.getProxy().getToDoService()).thenReturn(Mockito.mock(ToDoService.class));
        ToDoDTO dto = new ToDoDTO();
        //when

        UpdateToDoEvent.fireEvent(toDoSystem, dto);

        //then
        Mockito.verifyNoInteractions(toDoSystem.getProxy());
    }

    @Test
    void whenUpdateTodoEventTriggeredThenCallRepositoryMethod() {
        VaadinMock.mockSystem(new UserDTO("guest"),ToDoPermissions.TODO_UPDATE);
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getProxy()).thenReturn(Mockito.mock(ToDoProxy.class));
        Mockito.when(toDoSystem.getProxy().getToDoService()).thenReturn(Mockito.mock(ToDoService.class));
        ToDoDTO dto = new ToDoDTO();
        //when
        try (MockedStatic<Notification> notificationMock = mockStatic(Notification.class)) {
            Notification notify = Mockito.mock(Notification.class);
            notificationMock.when(() -> Notification.show(any())).thenReturn(notify);

            UpdateToDoEvent.fireEvent(toDoSystem, dto);
        }
        //then
        Mockito.verify(toDoSystem.getProxy().getToDoService()).updateToDo(dto);
    }

    @Test
    void whenErrorWhileUpdatingToDoItemThenShowErrorNotification() {
        VaadinMock.mockSystem(new UserDTO("guest"),ToDoPermissions.TODO_UPDATE);
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        Mockito.when(toDoSystem.getProxy()).thenReturn(Mockito.mock(ToDoProxy.class));
        ToDoService toDoService = Mockito.mock(ToDoService.class);
        Mockito.when(toDoSystem.getProxy().getToDoService()).thenReturn(toDoService);
        doThrow(OptimisticLockException.class).when(toDoService).updateToDo(any());
        ToDoDTO dto = new ToDoDTO();
        //when
        try (MockedStatic<Notification> notificationMock = mockStatic(Notification.class)) {
            Notification notify = Mockito.mock(Notification.class);
            notificationMock.when(() -> Notification.show(any())).thenReturn(notify);

            UpdateToDoEvent.fireEvent(toDoSystem, dto);

            //then
            Mockito.verify(notify).addThemeVariants(NotificationVariant.LUMO_ERROR);
            Mockito.verify(notify).open();
        }

    }
}