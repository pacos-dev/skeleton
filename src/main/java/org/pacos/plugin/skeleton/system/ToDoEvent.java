package org.pacos.plugin.skeleton.system;


import org.pacos.base.event.EventType;

/**
 * Event type defined by this module
 */
public enum ToDoEvent implements EventType {

    ITEM_TODO_CREATED,
    DELETE_SHORTCUT_TRIGGER,
    ITEM_TODO_REMOVED;

}
