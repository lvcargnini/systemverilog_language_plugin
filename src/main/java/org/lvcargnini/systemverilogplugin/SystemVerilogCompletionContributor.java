package org.lvcargnini.systemverilogplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.lvcargnini.systemverilogplugin.psi.SystemVerilogTypes;
import org.jetbrains.annotations.NotNull;

public class SystemVerilogCompletionContributor extends CompletionContributor {
    public SystemVerilogCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SystemVerilogTypes.VALUE).withLanguage(SystemVerilogLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
}
