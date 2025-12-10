package org.pacos.plugin.skeleton.system.event;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.pacos.base.session.UserSession;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoSystem;

public final class UpdateToDoEvent {

    private UpdateToDoEvent() {
    }

    public static void fireEvent(ToDoSystem toDoSystem, ToDoDTO dto) {
        if (!UserSession.getCurrent().hasActionPermission(ToDoPermissions.TODO_UPDATE)) {
            return;
        }
        try {
            toDoSystem.getProxy().getToDoService().updateToDo(dto);
            Notification.show("Updated todo id: " + dto.getId()).open();
        } catch (Exception e) {
            Notification notification = Notification.show(e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
        }
    }
}
