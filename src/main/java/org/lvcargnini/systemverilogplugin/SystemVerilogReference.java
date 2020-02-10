package org.lvcargnini.systemverilogplugin;

import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.lvcargnini.systemverilogplugin.psi.SystemVerilogProperty;
import org.jetbrains.annotations.*;

import java.util.*;


public class SystemVerilogReference  extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public SystemVerilogReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (SystemVerilogProperty property : properties) {
            results.add(new PsiElementResolveResult((PsiElement) property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final SystemVerilogProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                variants.add(LookupElementBuilder.create(property).
                        withIcon(SystemVerilogIcons.FILE).
                        withTypeText(property.getName())//getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
