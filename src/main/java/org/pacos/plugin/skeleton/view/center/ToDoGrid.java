package org.pacos.plugin.skeleton.view.center;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.pacos.base.session.UserSession;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoEvent;
import org.pacos.plugin.skeleton.system.ToDoSystem;
import org.pacos.plugin.skeleton.system.event.RemoveToDoEvent;

public class ToDoGrid extends Grid<ToDoDTO> {

    private final List<ToDoDTO> items;
    private final ToDoSystem toDoSystem;

    public ToDoGrid(ToDoSystem toDoSystem) {
        super(ToDoDTO.class, false);
        this.toDoSystem = toDoSystem;

        configureGridComponent();

        configureColumns(toDoSystem);

        this.items = toDoSystem.getProxy().getToDoService().loadForUser(toDoSystem.getUserId());
        setItems(items);

        GridLineEditorDecorator.configure(this, toDoSystem);

        toDoSystem.subscribe(ToDoEvent.ITEM_TODO_CREATED, e -> newElementCreated((ToDoDTO) e));
        toDoSystem.subscribe(ToDoEvent.ITEM_TODO_REMOVED, e -> removeTodo((ToDoDTO) e));
        toDoSystem.subscribe(ToDoEvent.DELETE_SHORTCUT_TRIGGER, e -> removeSelectedToDo());
    }

    private void configureColumns(ToDoSystem toDoSystem) {
        addColumn(ToDoDTO::getName).setHeader("Name").setResizable(true).setKey("name");
        addColumn(ToDoDTO::isActive).setHeader("Active").setResizable(true).setKey("active");
        if (UserSession.getCurrent().hasPermission(ToDoPermissions.TODO_REMOVE)) {
            addColumn(new ComponentRenderer<>(todElem -> {
                Button button = new Button(VaadinIcon.TRASH.create(),
                        e -> RemoveToDoEvent.fireEvent(toDoSystem, todElem));
                button.addThemeVariants(ButtonVariant.LUMO_SMALL);
                button.addThemeVariants(ButtonVariant.LUMO_ERROR);
                return button;
            }));
        }
    }

    private void configureGridComponent() {
        addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);

        addClassName("no-select");
        setSizeFull();

        setSelectionMode(SelectionMode.SINGLE);
    }

    private void removeSelectedToDo() {
        if (!getSelectedItems().isEmpty()) {
            RemoveToDoEvent.fireEvent(toDoSystem, getSelectedItems().iterator().next());
        }
    }

    private void removeTodo(ToDoDTO e) {
        items.remove(e);
        setItems(items);
    }

    private void newElementCreated(ToDoDTO e) {
        items.add(e);
        setItems(items);
    }
}
