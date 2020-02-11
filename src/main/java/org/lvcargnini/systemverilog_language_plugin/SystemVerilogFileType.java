package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class SystemVerilogFileType extends LanguageFileType  {
    public static final SystemVerilogFileType INSTANCE = new SystemVerilogFileType();

//    static {
//        INSTANCE = new SystemVerilogFileType();
//    }

    private SystemVerilogFileType() {
        super(SystemVerilogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "SystemVerilog file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SystemVerilog language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sv";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SystemVerilogIcons.FILE;
    }
}

