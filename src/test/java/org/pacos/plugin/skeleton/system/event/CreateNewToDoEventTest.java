package org.pacos.plugin.skeleton.system.event;

import com.vaadin.flow.component.notification.Notification;
import org.pacos.base.session.UserDTO;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.backend.todo.ToDoService;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoEvent;
import org.pacos.plugin.skeleton.system.ToDoSystem;
import org.pacos.plugin.skeleton.view.VaadinMock;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class CreateNewToDoEventTest {

    @Test
    void whenNoPermissionsThenDoNothing() {
        //given
        VaadinMock.mockSystem(new UserDTO("guest"));
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        ToDoService service = mock(ToDoService.class);
        when(toDoSystem.getProxy()).thenReturn(mock(ToDoProxy.class));
        when(toDoSystem.getProxy().getToDoService()).thenReturn(service);

        //WHEN
        CreateNewToDoEvent.fireEvent(toDoSystem);

        //then
        Mockito.verifyNoInteractions(service);
    }

    @Test
    void whenCreateNewToDoThenCallService() {
        //given
        VaadinMock.mockSystem(new UserDTO("guest"), ToDoPermissions.TODO_ADD);
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        ToDoService service = mock(ToDoService.class);
        when(toDoSystem.getProxy()).thenReturn(mock(ToDoProxy.class));
        when(toDoSystem.getProxy().getToDoService()).thenReturn(service);

        //when
        try(MockedStatic<Notification> notificationMock = mockStatic(Notification.class)){
            Notification notify = Mockito.mock(Notification.class);
            notificationMock.when(()->Notification.show(any())).thenReturn(notify);

            CreateNewToDoEvent.fireEvent(toDoSystem);
        }
        //then
        Mockito.verify(service).saveNewToDo(any());
    }

    @Test
    void whenNewToDoCreatedThenNotifySystem() {
        //given
        VaadinMock.mockSystem(new UserDTO("guest"), ToDoPermissions.TODO_ADD);
        ToDoSystem toDoSystem = Mockito.mock(ToDoSystem.class);
        ToDoService service = mock(ToDoService.class);
        when(toDoSystem.getProxy()).thenReturn(mock(ToDoProxy.class));
        when(toDoSystem.getProxy().getToDoService()).thenReturn(service);
        when(service.saveNewToDo(any())).thenReturn(1);

        ToDoDTO dto = new ToDoDTO();
        dto.setId(1);

        //when
        try(MockedStatic<Notification> notificationMock = mockStatic(Notification.class)){
            Notification notify = Mockito.mock(Notification.class);
            notificationMock.when(()->Notification.show(any())).thenReturn(notify);

            CreateNewToDoEvent.fireEvent(toDoSystem);
        }
        //then
        Mockito.verify(toDoSystem).notify(ToDoEvent.ITEM_TODO_CREATED,dto);
    }
}