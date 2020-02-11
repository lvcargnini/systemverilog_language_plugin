package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.codeInsight.daemon.*;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SystemVerilogLineMarkerProvider  extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
        if (element instanceof PsiJavaTokenImpl && element.getParent() instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element.getParent();
            String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
            if (value != null && value.startsWith("SystemVerilog" + ":")) {
                Project project = element.getProject();
                final List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project, value.substring(7));
                if (properties.size() > 0) {
                    NavigationGutterIconBuilder<PsiElement> builder =
                            NavigationGutterIconBuilder.create(SystemVerilogIcons.FILE).
                                    setTargets(properties).
                                    setTooltipText("Navigate to a SystemVerilog property");
                    result.add(builder.createLineMarkerInfo(element));
                }
            }
        }
    }   
}
