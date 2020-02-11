package org.lvcargnini.systemverilog_language_plugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.lvcargnini.systemverilog_language_plugin.SystemVerilogFileType;

public class SystemVerilogElementFactory {
    public static SystemVerilogProperty createProperty(Project project, String name) {
        final SystemVerilogFile file = createFile(project, name);
        return (SystemVerilogProperty) file.getFirstChild();
    }

    public static SystemVerilogFile createFile(Project project, String text) {
        String name = "dummy.sv";
        return (SystemVerilogFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SystemVerilogFileType.INSTANCE, text);
    }
}
