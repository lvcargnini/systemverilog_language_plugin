package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.lang.cacheBuilder.*;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.*;
import com.intellij.psi.tree.TokenSet;
import org.lvcargnini.systemverilog_language_plugin.psi.*;
import org.jetbrains.annotations.*;

public class SystemVerilogFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new SystemVerilogLexerAdapter(),
                TokenSet.create(SystemVerilogTypes.KEY),
                TokenSet.create(SystemVerilogTypes.COMMENT),
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof SystemVerilogProperty) {
            return "simple property";
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof SystemVerilogProperty) {
            return ((SystemVerilogProperty) element).getKey();
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof SystemVerilogProperty) {
            return ((SystemVerilogProperty) element).getKey() + ":" + ((SystemVerilogProperty) element).getValue();
        } else {
            return "";
        }
    }
}
