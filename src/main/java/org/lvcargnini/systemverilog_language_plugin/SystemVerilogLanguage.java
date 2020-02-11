package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.lang.Language;

public class SystemVerilogLanguage extends Language {
    public static final SystemVerilogLanguage INSTANCE = new SystemVerilogLanguage();

    private SystemVerilogLanguage() {
        super("SystemVerilog");
    }
}