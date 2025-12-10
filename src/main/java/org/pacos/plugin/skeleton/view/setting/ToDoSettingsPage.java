package org.pacos.plugin.skeleton.view.setting;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.pacos.base.component.setting.SettingPageLayout;
import org.pacos.base.session.UserSession;
import org.pacos.base.utils.component.InfoBox;
import org.pacos.base.variable.VariableProcessor;
import org.pacos.base.window.shortcut.ShortcutType;
import org.pacos.plugin.skeleton.backend.ToDoVariableProvider;
import org.vaadin.addons.variablefield.data.Scope;
import org.vaadin.addons.variablefield.field.VariableTextField;

import java.util.Collections;
import java.util.List;

/**
 * This is configuration panel for example module that will be displayed in system setting module
 */
public class ToDoSettingsPage extends SettingPageLayout {

    private static final String DESCRIPTION =
            "This is an example page that presents configuration for the todo module";


    private static final String EXAMPLE = "Below is an example of a configured variable provider 'ToDoVariableProvider' " +
            " The Variable Field has been configured to only display variables from the ToDo module scope";


    private final VariableProcessor variableProcessor;
    private final VariableTextField variableField;
    private final TextField transformResultField;

    public ToDoSettingsPage(VariableProcessor variableProcessor) {
        this.variableProcessor = variableProcessor;

        add(new InfoBox(DESCRIPTION));
        add(new Hr());
        add(new InfoBox(EXAMPLE));

        this.variableField = new VariableTextField(ToDoVariableProvider.todoScope);
        this.variableField.setWidth(100, Unit.PERCENTAGE);
        this.variableField.setValue("This is an example {{todo}} variable with {{test}}");

        this.transformResultField = new TextField();
        this.transformResultField.setWidth(100, Unit.PERCENTAGE);
        this.transformResultField.setEnabled(false);

        add(variableField);
        add(new Button("Transform", e -> save()));
        add(transformResultField);

    }

    void save() {
        List<Scope> scopes = Collections.singletonList(ToDoVariableProvider.todoScope);
        String transformedMsg = variableProcessor.process(scopes, variableField.getValue(), UserSession.getCurrent().getUser());
        transformResultField.setValue(transformedMsg);
    }

    @Override
    public void onShortCutDetected(ShortcutType shortcutType) {
        if(shortcutType.isSave()){
            save();
        }
    }
}