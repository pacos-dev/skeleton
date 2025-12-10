package org.pacos.plugin.skeleton.system;

import org.pacos.base.event.SystemEvent;
import org.pacos.base.session.UserSession;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.view.PanelTodo;

/**
 * This class is used to manage all events inside the module.
 */
public class ToDoSystem extends SystemEvent<ToDoEvent> {

    private final PanelTodo panel;
    private final ToDoProxy toDoProxy;

    public ToDoSystem(PanelTodo panel, ToDoProxy toDoProxy) {
        this.panel = panel;
        this.toDoProxy = toDoProxy;
    }

    public int getUserId() {
        return UserSession.getCurrent().getUserId();
    }

    public ToDoProxy getProxy() {
        return toDoProxy;
    }

    public PanelTodo getPanel() {
        return panel;
    }
}
