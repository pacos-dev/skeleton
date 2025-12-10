package org.pacos.plugin.skeleton.backend.camunda;

import java.util.List;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;
import org.pacos.base.camunda.BlockFormHandler;
import org.pacos.base.camunda.BlockMetadata;
import org.pacos.base.camunda.ExecutableBlock;
import org.pacos.base.camunda.ProcessVariableManager;
import org.pacos.base.camunda.ResultVariable;
import org.pacos.base.session.UserSession;
import org.pacos.plugin.skeleton.backend.ToDoProxy;
import org.pacos.plugin.skeleton.backend.todo.dto.ToDoDTO;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.addons.variablefield.data.Scope;

/**
 * This is an example of executable block implementation for automation plugin
 * ToDoRecord is a bean that is used to transfer data from the UI to the execution
 */
@Component
public class NewTodoExecutionBlock implements ExecutableBlock<ToDoRecord> {

    private static final Logger LOG = LoggerFactory.getLogger(NewTodoExecutionBlock.class);
    private final ToDoProxy toDoProxy;

    @Autowired
    public NewTodoExecutionBlock(ToDoProxy toDoProxy) {
        this.toDoProxy = toDoProxy;
    }

    @Override
    public BlockMetadata basicData() {
        return new BlockMetadata() {

            /**
             * @return name that will be displayed on UI for this block
             */
            @Override
            public String name() {
                return "Add ToDo item";
            }

            /**
             * @return delegate name used by camunda to fin correct spring bean (NewTodoExecutionBlock in this case)
             */
            @Override
            public String camundaDelegateName() {
                return "newToDoItem";
            }

            /**
             * @return The group in the tree in the "Available blocks" tab in which this block will be available
             */
            @Override
            public String[] group() {
                return new String[] { "ToDo" };
            }

            /**
             * @return Array of variables produce by this block during execution
             */
            @Override
            public ResultVariable[] resultVariables() {
                return new ResultVariable[] { new ResultVariable("id", "Id of added record") };
            }
        };
    }

    @Override
    public BlockFormHandler<ToDoRecord> blockForm() {
        return new BlockFormHandler<ToDoRecord>() {

            /**
             * @return class for parametrized type
             */
            @Override
            public Class<ToDoRecord> beanClas() {
                return ToDoRecord.class;
            }

            /**
             *
             * @param verticalLayout add created components to this layout
             * @param scopes list of scopes
             * @return binder for configured type
             */
            @Override
            public Binder<ToDoRecord> createForm(VerticalLayout verticalLayout, List<Scope> scopes) {
                Binder<ToDoRecord> binder = new Binder<>(ToDoRecord.class);
                TextField name = new TextField("Name");
                Checkbox active = new Checkbox("Active");
                IntegerField userId = new IntegerField("User id");
                userId.setVisible(false);
                binder.forField(name)
                        .withValidator(new StringLengthValidator("Allowed length [1-255]", 1, 255))
                        .bind("name");
                binder.forField(active).bind("active");
                binder.forField(userId).bind("userId");
                verticalLayout.add(name, active, userId);

                return binder;
            }

            /**
             * Write bean
             * @param binder created by createForm method
             * @return parameterized object class
             * @throws ValidationException based on configured validators
             */
            @Override
            public ToDoRecord writeBean(Binder<?> binder) throws ValidationException {
                ToDoRecord toDoRecord = (ToDoRecord) binder.writeRecord();
                //Override user id only for presentation purpose
                return new ToDoRecord(toDoRecord.name(),toDoRecord.active(),UserSession.getCurrent().getUserId());
            }
        };
    }

    /**
     * This method is called by camunda.
     *
     * @param processVariableManager is used to read and write variables from the process
     * @param toDoRecord             mapped object configured by the user
     * @param scopes                 list of allowed scopes
     * @throws Exception if occurred
     */
    @Override
    public void execute(ProcessVariableManager processVariableManager, @Nullable ToDoRecord toDoRecord,
            List<Scope> scopes) throws Exception {
        LOG.debug("Executing NewTodoExecutionBlock");
        if (toDoRecord == null) {
            throw new RuntimeException("ToDoRecord is null");
        }
        ToDoDTO dto = new ToDoDTO();
        dto.setName(toDoRecord.name());
        dto.setActive(toDoRecord.active());
        dto.setUserId(toDoRecord.userId());
        int id = toDoProxy.getToDoService().saveNewToDo(dto);

        processVariableManager.setVariable("todo_id", id);

        LOG.debug("Finished NewTodoExecutionBlock");
    }
}
