package org.lvcargnini.systemverilog_language_plugin;


import com.intellij.application.options.*;
import com.intellij.psi.codeStyle.*;
import org.jetbrains.annotations.*;

public class SystemVerilogCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new SystemVerilogCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "SytemVerilog";
    }


    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new SytemVerilogCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class SytemVerilogCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public SytemVerilogCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(SystemVerilogLanguage.INSTANCE, currentSettings, settings);
        }
    }
}