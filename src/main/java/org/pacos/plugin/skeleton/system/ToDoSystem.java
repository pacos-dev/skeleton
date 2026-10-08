package org.pacos.plugin.skeleton.system;

import org.pacos.base.event.SystemEvent;
import org.pacos.base.session.UserSession;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.view.PanelTodo;

/**
 * Coordinates plugin-local events for one ToDo window instance.
 *
 * The object deliberately keeps the window and proxy together so UI event handlers can communicate without exposing
 * the implementation details of the underlying service layer.
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
