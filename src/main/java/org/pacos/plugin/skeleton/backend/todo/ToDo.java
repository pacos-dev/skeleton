package org.pacos.plugin.skeleton.backend.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "TODO_LIST")
@SequenceGenerator(
        name = "TODO-SEQ-GEN",
        sequenceName = "TODO_SEQ",
        allocationSize = 1)
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO-SEQ-GEN")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "done")
    private boolean done;

    public ToDo() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    Integer getId() {
        return id;
    }

    Integer getUserId() {
        return userId;
    }

    void setUserId(Integer userId) {
        this.userId = userId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    boolean isDone() {
        return done;
    }

    void setDone(boolean active) {
        this.done = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(id, toDo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
