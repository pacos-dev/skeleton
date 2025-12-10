package org.pacos.plugin.skeleton.view.config;

import org.pacos.base.session.UserSession;
import org.pacos.base.window.DesktopWindow;
import org.pacos.base.window.config.WindowConfig;
import org.pacos.plugin.skeleton.view.PanelTodo;
import org.springframework.stereotype.Component;

/**
 * This class provides a configuration for the module window. Because its spring component, its ensure that this
 * implementation will be found during package scanning.
 * Based on that component, pacos will be extended about new functionality.
 * This configuration must be injected to the window implementation {@link org.pacos.plugin.skeleton.view.PanelTodo}.
 */
@Component
public class MyTodoConfig implements WindowConfig {

    /**
     * Window title. Will be displayed as a label of opened modal
     */
    @Override
    public String title() {
        return "ToDo list";
    }

    /**
     * Define icon resources which will be used as a module icon
     */
    @Override
    public String icon() {
        return "img/icon/to-do-list.png";
    }

    /**
     * Defines activator class which will be initialized after user demand
     */
    @Override
    public Class<? extends DesktopWindow> activatorClass() {
        return PanelTodo.class;
    }

    /**
     * If is application then the module will be displayed in application drop-down menu
     * and could be pinned to dock. If false the direct usage of this configuration will be on developer side.
     */
    @Override
    public boolean isApplication() {
        return true;
    }

    /**
     * Defines if application could have multiple instance in one session.
     * (For example some windows required to have more than one active instance)
     */
    @Override
    public boolean isAllowMultipleInstance() {
        return false;
    }

    /**
     * Additional restriction that tell the pacos if module is available for current session.
     * By default, the value is true - available for all sessions.
     * If the limitation is needed eg. because of the defined permission, then the required conditions should be
     * implemented here.
     */
    @Override
    public boolean isAllowedForCurrentSession(UserSession userSession) {
        return true;
    }

}