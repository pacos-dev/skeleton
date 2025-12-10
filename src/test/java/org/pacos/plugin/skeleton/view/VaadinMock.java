package org.pacos.plugin.skeleton.view;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.vaadin.flow.component.ShortcutRegistration;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.internal.CurrentInstance;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import org.pacos.base.event.UISystem;
import org.pacos.base.security.Permission;
import org.pacos.base.security.PermissionConfig;
import org.pacos.base.session.UserDTO;
import org.pacos.base.session.UserSession;
import org.pacos.base.window.manager.ApplicationManager;
import org.pacos.base.window.manager.ClipboardManager;
import org.pacos.base.window.manager.DownloadManager;
import org.pacos.base.window.manager.ShortcutManager;
import org.pacos.base.window.manager.VariableManager;
import org.pacos.base.window.manager.WindowManager;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class VaadinMock {

    private VaadinMock() {
    }

    //Hold instance, otherwise garbage collector will remove it from CurrentInstance
    private static VaadinSession vaadinSession;
    //Hold instance, otherwise garbage collector will remove it from CurrentInstance
    private static UserSession userSession;
    //Hold instance, otherwise garbage collector will remove it from CurrentInstance
    private static UI ui;
    //Hold instance, otherwise garbage collector will remove it from CurrentInstance
    private static UISystem uiSystem;

    public static UISystem mockSystem(UserDTO userDTO, Permission...permissions) {
        CurrentInstance.clearAll();

        VaadinMock.ui = Mockito.spy(new UI());
        doReturn(CompletableFuture.completedFuture(null)).when(ui).access(any());
        doReturn(Mockito.mock(Page.class)).when(ui).getPage();
        var permissionConfig =
                Arrays.stream(permissions).map(e ->
                        new PermissionConfig(e.getKey(), "ALLOW")).collect(Collectors.toSet());
        VaadinMock.userSession = spy(new UserSession(userDTO,permissionConfig));
        VaadinMock.vaadinSession = spy(new VaadinSession(Mockito.mock(VaadinService.class)));

        doReturn(userSession).when(vaadinSession).getAttribute(UserSession.class);
        CurrentInstance.set(UserSession.class, userSession);
        CurrentInstance.set(VaadinSession.class, vaadinSession);
        CurrentInstance.set(UI.class, ui);

        ShortcutManager shortcutManager = mock(ShortcutManager.class);
        when(shortcutManager.registerShortcut(any(), any(), any())).thenReturn(mock(ShortcutRegistration.class));

        VaadinMock.uiSystem = spy(new UISystem(mock(DownloadManager.class), mock(VariableManager.class), mock(WindowManager.class),
                mock(ApplicationManager.class), shortcutManager, mock(ClipboardManager.class)));
        userSession.setUiSystem(uiSystem);
        return uiSystem;
    }
}
