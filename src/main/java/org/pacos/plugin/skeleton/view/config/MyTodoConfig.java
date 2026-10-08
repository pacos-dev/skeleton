package org.pacos.plugin.skeleton.view.config;

import org.pacos.base.session.UserSession;
import org.pacos.base.window.DesktopWindow;
import org.pacos.base.window.config.WindowConfig;
import org.pacos.plugin.skeleton.view.PanelTodo;
import org.springframework.stereotype.Component;

/**
 * Registers the ToDo desktop application with PacOS.
 *
 * Because this class is a Spring component in the plugin configuration scan, PacOS discovers it as a
 * {@link WindowConfig} implementation and uses it to expose the {@link PanelTodo} window.
 */
@Component
public class MyTodoConfig implements WindowConfig {

    /**
     * @return title displayed in the window header
     */
    @Override
    public String title() {
        return "ToDo list";
    }

    /**
     * @return classpath-relative icon path used by the application list, dock and window header
     */
    @Override
    public String icon() {
        return "img/icon/to-do-list.png";
    }

    /**
     * @return prototype window class that PacOS creates when the application is activated
     */
    @Override
    public Class<? extends DesktopWindow> activatorClass() {
        return PanelTodo.class;
    }

    /**
     * @return whether this window is visible in the PacOS application list and can be pinned
     */
    @Override
    public boolean isApplication() {
        return true;
    }

    /**
     * @return whether more than one instance may be opened in the same user session
     */
    @Override
    public boolean isAllowMultipleInstance() {
        return false;
    }

    /**
     * Controls whether the application is available to the current session.
     * Use this hook for session-level restrictions; enforce action permissions separately at the action boundary.
     */
    @Override
    public boolean isAllowedForCurrentSession(UserSession userSession) {
        return true;
    }
}
