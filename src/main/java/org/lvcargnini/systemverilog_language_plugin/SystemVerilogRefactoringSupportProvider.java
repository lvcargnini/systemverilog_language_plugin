package org.lvcargnini.systemverilog_language_plugin;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogProperty;

public class SystemVerilogRefactoringSupportProvider  extends RefactoringSupportProvider  {
    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
        return element instanceof SystemVerilogProperty;
    }
}
