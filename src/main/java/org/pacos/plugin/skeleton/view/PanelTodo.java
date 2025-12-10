package org.pacos.plugin.skeleton.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.pacos.base.utils.component.ButtonUtils;
import org.pacos.base.window.DesktopWindow;
import org.pacos.base.window.shortcut.ShortcutType;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.security.ToDoPermissions;
import org.pacos.plugin.skeleton.system.ToDoEvent;
import org.pacos.plugin.skeleton.system.ToDoSystem;
import org.pacos.plugin.skeleton.system.event.CreateNewToDoEvent;
import org.pacos.plugin.skeleton.view.center.ToDoGrid;
import org.pacos.plugin.skeleton.view.config.MyTodoConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This class represents ToDoModule Panel.
 * Is responsible for creating window on UI that will represents To Do List module
 * <p>
 * Contains additional css script which will be added to the Page on browser side after entering to this module.
 * <p>
 * The module is marked as a prototype because the pacos is responsible for creating this element and injecting
 * dependencies. It does this on the fly during each module creation.
 */
@Component
@Scope("prototype")
public class PanelTodo extends DesktopWindow {

    protected PanelTodo(MyTodoConfig moduleConfig, ToDoProxy toDoProxy) {
        super(moduleConfig);
        //Load css only when panel is initialized. Vaadin @CssImport do not work as expected in desktop mode
        addAttachListener(attachEvent -> UI.getCurrent().getPage().addStyleSheet("frontend/css/todo.css"));
        //Create module system event manager
        ToDoSystem toDoSystem = new ToDoSystem(this, toDoProxy);
        Button addNewItemBtn =
                new ButtonUtils("Add new item", e -> CreateNewToDoEvent.fireEvent(toDoSystem))
                        .withVisibleForPermission(ToDoPermissions.TODO_ADD);
        //UI configuration
        //Create button with click listener

        //Create grid
        ToDoGrid toDoGrid = new ToDoGrid(toDoSystem);
        //Create info box
        Span infoBox = new Span();
        infoBox.add(VaadinIcon.INFO_CIRCLE_O.create());
        infoBox.add(new Text("Double click on row to edit, or use 'del' key to remove selected entry"));
        infoBox.addClassNames("info");

        //add all created components to the window layout. DesktopWindow by default uses VerticalLayout
        add(infoBox);
        add(toDoGrid);
        add(addNewItemBtn);


        //register shortcut for deletion item from grid
        super.registerShortcut(ShortcutType.DELETE, e -> toDoSystem.notify(ToDoEvent.DELETE_SHORTCUT_TRIGGER));
    }
}
