package org.lvcargnini.systemverilog_language_plugin.psi.impl;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogNamedElement;
import org.jetbrains.annotations.NotNull;


public abstract class SystemVerilogNamedElementImpl  extends ASTWrapperPsiElement implements SystemVerilogNamedElement {
    public SystemVerilogNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}
