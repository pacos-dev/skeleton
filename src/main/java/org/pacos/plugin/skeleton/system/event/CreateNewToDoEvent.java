package org.pacos.plugin.skeleton.system.event;

import com.vaadin.flow.component.notification.Notification;
import org.pacos.base.session.UserSession;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoEvent;
import org.pacos.plugin.skeleton.system.ToDoSystem;

public final class CreateNewToDoEvent {

    private CreateNewToDoEvent() {
    }

    public static void fireEvent(ToDoSystem toDoSystem) {
        if (UserSession.getCurrent().hasActionPermission(ToDoPermissions.TODO_ADD)) {

            ToDoDTO dto = new ToDoDTO();
            dto.setName("New todo");
            dto.setActive(true);
            dto.setUserId(toDoSystem.getUserId());

            int todoId = toDoSystem.getProxy().getToDoService().saveNewToDo(dto);
            Notification.show("Stored new todo with id: " + todoId).open();
            dto.setId(todoId);

            toDoSystem.notify(ToDoEvent.ITEM_TODO_CREATED, dto);
        }
    }
}
