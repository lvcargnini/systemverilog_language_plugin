package org.lvcargnini.systemverilogplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.lvcargnini.systemverilogplugin.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class SystemVerilogFile extends PsiFileBase {
    public SystemVerilogFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SystemVerilogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SystemVerilogFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "SystemVerilog File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
