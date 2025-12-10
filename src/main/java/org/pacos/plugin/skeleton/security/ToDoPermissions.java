package org.pacos.plugin.skeleton.security;

import org.pacos.base.security.Permission;

/**
 * Permission implementation is read by core using reflection. Must be in 'org.pacos' package
 */
public enum ToDoPermissions implements Permission {
    TODO_ADD("todo.add", "Add new todo item", "todo","Show 'Add new todo' button in todo plugin"),
    TODO_REMOVE("todo.remove", "Remove todo item", "todo","Show 'remove' column in todo plugin"),
    TODO_UPDATE("todo.update", "Update todo item", "todo","Show 'update' button in todo plugin");

    private final String key;
    private final String label;
    private final String category;
    private final String description;

    ToDoPermissions(String key, String label, String category, String description) {
        this.key = key;
        this.label = label;
        this.category = category;
        this.description = description;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
