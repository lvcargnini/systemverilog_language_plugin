// This is a generated file. Not intended for manual editing.
package org.lvcargnini.systemverilog_language_plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogTypes.*;
import org.lvcargnini.systemverilog_language_plugin.psi.*;

public class SystemVerilogPropertyImpl extends SystemVerilogNamedElementImpl implements SystemVerilogProperty {

  public SystemVerilogPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SystemVerilogVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SystemVerilogVisitor) accept((SystemVerilogVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getKey() {
    return SystemVerilogPsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return SystemVerilogPsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return SystemVerilogPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return SystemVerilogPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return SystemVerilogPsiImplUtil.getNameIdentifier(this);
  }

}
