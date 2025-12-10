package org.pacos.plugin.skeleton.system.event;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import org.pacos.base.session.UserSession;
import org.pacos.base.utils.component.VerticalLayoutUtils;
import org.pacos.base.window.config.impl.ConfirmationWindowConfig;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoEvent;
import org.pacos.plugin.skeleton.system.ToDoSystem;

public final class RemoveToDoEvent {

    private RemoveToDoEvent() {
    }

    public static void fireEvent(ToDoSystem toDoSystem, ToDoDTO dto) {
        if (UserSession.getCurrent().hasActionPermission(ToDoPermissions.TODO_ADD)) {

            ConfirmationWindowConfig confirmationWindow =
                    new ConfirmationWindowConfig(() -> onConfirm(toDoSystem, dto));

            confirmationWindow.setContent(VerticalLayoutUtils.defaults(
                    new Span("Are you sure you want to remove this TODO item?")
            ));

            toDoSystem.getPanel().addWindow(confirmationWindow);
        }
    }

    static boolean onConfirm(ToDoSystem toDoSystem, ToDoDTO dto) {
        toDoSystem.getProxy().getToDoService().removeToDo(dto);
        toDoSystem.notify(ToDoEvent.ITEM_TODO_REMOVED, dto);
        Notification.show("Item was removed").open();
        return true;
    }
}
