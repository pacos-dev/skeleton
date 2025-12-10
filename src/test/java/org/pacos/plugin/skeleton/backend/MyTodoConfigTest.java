package org.pacos.plugin.skeleton.backend;

import org.pacos.plugin.skeleton.view.PanelTodo;
import org.pacos.plugin.skeleton.view.config.MyTodoConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyTodoConfigTest {

    private final MyTodoConfig myTodoConfig = new MyTodoConfig();

    @Test
    void whenTitleCalledThenReturnCorrectTitle() {
        assertEquals("ToDo list", myTodoConfig.title());
    }

    @Test
    void whenIconCalledThenReturnCorrectIconPath() {
        assertEquals("img/icon/to-do-list.png", myTodoConfig.icon());
    }

    @Test
    void whenActivatorClassCalledThenReturnCorrectClass() {
        assertEquals(PanelTodo.class, myTodoConfig.activatorClass());
    }

    @Test
    void whenIsApplicationCalledThenReturnTrue() {
        assertTrue(myTodoConfig.isApplication());
    }

    @Test
    void whenIsAllowMultipleInstanceCalledThenReturnFalse() {
        assertFalse(myTodoConfig.isAllowMultipleInstance());
    }

    @Test
    void whenIsAllowedForCurrentSessionCalledThenReturnTrue() {
        assertTrue(myTodoConfig.isAllowedForCurrentSession(null));
    }
}
