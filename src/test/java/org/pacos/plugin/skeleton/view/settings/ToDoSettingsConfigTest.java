package org.pacos.plugin.skeleton.view.settings;

import org.pacos.base.component.setting.SettingPageLayout;
import org.pacos.base.variable.VariableProcessor;
import org.pacos.base.window.shortcut.ShortcutType;
import org.pacos.plugin.skeleton.view.setting.ToDoSettingsConfig;
import org.pacos.plugin.skeleton.view.setting.ToDoSettingsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoSettingsConfigTest {

    private ToDoSettingsConfig toDoSettingsConfig;

    @BeforeEach
    void setUp() {
        VariableProcessor mockVariableProcessor = Mockito.mock(VariableProcessor.class);
        toDoSettingsConfig = new ToDoSettingsConfig(mockVariableProcessor);
    }

    @Test
    void whenGetTitleCalledThenReturnCorrectTitle() {
        assertEquals("ToDo", toDoSettingsConfig.getTitle());
    }

    @Test
    void whenGenerateContentCalledThenReturnToDoSettingsPage() {
        assertNotNull(toDoSettingsConfig.generateContent());
        assertInstanceOf(ToDoSettingsPage.class, toDoSettingsConfig.generateContent());
    }

    @Test
    void whenGetOrderCalledThenReturnCorrectOrder() {
        assertEquals(100, toDoSettingsConfig.getOrder());
    }

    @Test
    void whenOnDeleteCalledThenDoNothing() {
        SettingPageLayout content = toDoSettingsConfig.generateContent();
        assertDoesNotThrow(()->content.onShortCutDetected(ShortcutType.DELETE));
    }

    @Test
    void whenShouldBeDisplayedCalledThenReturnTrue() {
        assertTrue(toDoSettingsConfig.shouldBeDisplayed(null));
    }
}
