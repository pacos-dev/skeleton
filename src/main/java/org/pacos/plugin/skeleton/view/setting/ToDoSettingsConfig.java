package org.pacos.plugin.skeleton.view.setting;

import org.pacos.base.component.setting.SettingPageLayout;
import org.pacos.base.component.setting.SettingTab;
import org.pacos.base.session.UserSession;
import org.pacos.base.variable.VariableProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is responsible to provide setting page definition to the pacos.
 * The configured page will be displayed in system setting module
 * System setting module is available only for login users
 */
@Component
public class ToDoSettingsConfig implements SettingTab {

    private final VariableProcessor variableProcessor;

    @Autowired
    public ToDoSettingsConfig(VariableProcessor variableProcessor) {
        this.variableProcessor = variableProcessor;
    }

    @Override
    public String getTitle() {
        return "ToDo";
    }

    @Override
    public SettingPageLayout generateContent() {
        return new ToDoSettingsPage(variableProcessor);
    }

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public boolean shouldBeDisplayed(UserSession userSession) {
        //Configure permission for this view
        return true;
    }

}
