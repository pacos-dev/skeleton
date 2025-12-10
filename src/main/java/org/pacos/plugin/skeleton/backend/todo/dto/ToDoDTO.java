package org.pacos.plugin.skeleton.backend.todo.dto;

import java.util.Objects;

public class ToDoDTO {
    private int id;
    private String name;
    private boolean active;
    private int userId;

    public ToDoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ToDoDTO toDoDTO = (ToDoDTO) o;
        return getId() == toDoDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
