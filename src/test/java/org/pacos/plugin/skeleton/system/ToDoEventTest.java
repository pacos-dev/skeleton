package org.pacos.plugin.skeleton.system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoEventTest {

    @Test
    void whenEnumHasCorrectNamesThenMatchExpectedNames() {
        assertEquals("ITEM_TODO_CREATED", ToDoEvent.ITEM_TODO_CREATED.name());
        assertEquals("DELETE_SHORTCUT_TRIGGER", ToDoEvent.DELETE_SHORTCUT_TRIGGER.name());
        assertEquals("ITEM_TODO_REMOVED", ToDoEvent.ITEM_TODO_REMOVED.name());
    }
}