// This is a generated file. Not intended for manual editing.
package org.lvcargnini.systemverilog_language_plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.lvcargnini.systemverilog_language_plugin.psi.impl.*;

public interface SystemVerilogTypes {

  IElementType PROPERTY = new SystemVerilogElementType("PROPERTY");

  IElementType COMMENT = new SystemVerilogTokenType("COMMENT");
  IElementType CRLF = new SystemVerilogTokenType("CRLF");
  IElementType KEY = new SystemVerilogTokenType("KEY");
  IElementType SEPARATOR = new SystemVerilogTokenType("SEPARATOR");
  IElementType VALUE = new SystemVerilogTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new SystemVerilogPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
