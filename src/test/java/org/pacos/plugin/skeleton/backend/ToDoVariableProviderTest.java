package org.pacos.plugin.skeleton.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vaadin.addons.variablefield.data.Variable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoVariableProviderTest {

    private ToDoVariableProvider toDoVariableProvider;

    @BeforeEach
    void setUp() {
        toDoVariableProvider = new ToDoVariableProvider();
    }

    @Test
    void whenLoadVariablesCalledThenReturnCorrectVariables() {
        List<Variable> variables = toDoVariableProvider.loadVariables(ToDoVariableProvider.todoScope);

        assertEquals(2, variables.size());
        assertTrue(variables.stream().anyMatch(variable -> "test".equals(variable.getName())));
        assertTrue(variables.stream().anyMatch(variable -> "todo".equals(variable.getName())));
    }

    @Test
    void whenScopeSupportedReturnTrue() {
        assertTrue(toDoVariableProvider.isScopeSupported(ToDoVariableProvider.scopeName));
    }

    @Test
    void whenLoadVariableCalledWithExistingVariableThenReturnCorrectVariable() {
        Optional<Variable> variable = toDoVariableProvider.loadVariable(ToDoVariableProvider.todoScope, "test");

        assertTrue(variable.isPresent());
        assertEquals("test", variable.get().getName());
        assertEquals("init value", variable.get().getInitialValue());
    }

    @Test
    void whenLoadVariableCalledWithNonExistingVariableThenReturnEmptyOptional() {
        Optional<Variable> variable = toDoVariableProvider.loadVariable(ToDoVariableProvider.todoScope, "nonExisting");

        assertFalse(variable.isPresent());
    }
}
