// This is a generated file. Not intended for manual editing.
package org.lvcargnini.systemverilog_language_plugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SystemVerilogVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull SystemVerilogProperty o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull SystemVerilogNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
