package org.lvcargnini.systemverilog_language_plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.*;
import org.lvcargnini.systemverilog_language_plugin.SystemVerilogIcons;
import org.lvcargnini.systemverilog_language_plugin.psi.*;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;


import javax.swing.*;

public class SystemVerilogPsiImplUtil {

    public static String getKey(SystemVerilogProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SystemVerilogTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(SystemVerilogProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(SystemVerilogTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }
    // ...

    public static String getName(SystemVerilogProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(SystemVerilogProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SystemVerilogTypes.KEY);
        if (keyNode != null) {

            SystemVerilogProperty property = SystemVerilogElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SystemVerilogProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SystemVerilogTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    // ...
}
