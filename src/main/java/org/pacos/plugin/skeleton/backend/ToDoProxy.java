package org.pacos.plugin.skeleton.backend;

import org.pacos.plugin.skeleton.backend.todo.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class manages all the resources required by the view
 */
@Component
public class ToDoProxy {
    private final ToDoService toDoService;

    @Autowired
    public ToDoProxy(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    public ToDoService getToDoService() {
        return toDoService;
    }
}
