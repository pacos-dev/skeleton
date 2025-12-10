package org.pacos.plugin.skeleton.view.center;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.pacos.plugin.skeleton.system.ToDoSystem;
import org.pacos.plugin.skeleton.system.event.UpdateToDoEvent;

public final class GridLineEditorDecorator {

    private GridLineEditorDecorator() {
    }

    public static void configure(ToDoGrid grid, ToDoSystem system) {
        Editor<ToDoDTO> editor = grid.getEditor();
        Binder<ToDoDTO> binder = new Binder<>(ToDoDTO.class);
        editor.setBinder(binder);

        Grid.Column<ToDoDTO> nameCol = grid.getColumnByKey("name");
        Grid.Column<ToDoDTO> activeCol = grid.getColumnByKey("active");

        TextField nameField = new TextField();
        nameField.setWidthFull();
        nameField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        binder.forField(nameField)
                .asRequired()
                .bind(ToDoDTO::getName, ToDoDTO::setName);
        nameCol.setEditorComponent(nameField);
        saveOnEnterCloseOnEsc(nameField, editor);

        Checkbox activeField = new Checkbox();
        binder.forField(activeField)
                .bind(ToDoDTO::isActive, ToDoDTO::setActive);
        activeCol.setEditorComponent(activeField);

        grid.addItemDoubleClickListener(e -> editor.editItem(e.getItem()));
        editor.addCloseListener(e -> UpdateToDoEvent.fireEvent(system, e.getItem()));
    }

    public static void saveOnEnterCloseOnEsc(Component textField,
                                             Editor<ToDoDTO> editor) {
        textField.getElement().addEventListener("keydown", e -> {
                    if (editor.isOpen() && editor.getBinder().validate().isOk()) {
                        editor.cancel();
                    } else {
                        Notification.show("Can't save changes because of error in header form").open();
                    }
                })
                .setFilter("event.code === 'Escape' || event.code === 'Enter' || event.code === 'NumpadEnter'");
    }
}
