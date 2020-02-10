package org.lvcargnini.systemverilogplugin;

import com.intellij.lang.Language;

public class SystemVerilogLanguage extends Language {
    public static final SystemVerilogLanguage INSTANCE = new SystemVerilogLanguage();

    private SystemVerilogLanguage() {
        super("SystemVerilog");
    }
}