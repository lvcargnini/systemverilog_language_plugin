package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogProperty;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;

import java.util.List;


public class SystemVerilogAnnotator implements Annotator {
    // Define strings for the SystemVerilog language prefix - used for annotations, line markers, etc.
    public static final String SIMPLE_PREFIX_STR = "systemverilog";
    public static final String SIMPLE_SEPARATOR_STR = ":";

//    @Override
//    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
//        if (element instanceof PsiLiteralExpression) {
//            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
//            String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
//
//            if (value != null && value.startsWith("SystemVerilog" + ":")) {
//                Project project = element.getProject();
//                String key = value.substring(7);
//                List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project, key);
//                if (properties.size() == 1) {
//                    TextRange range = new TextRange(element.getTextRange().getStartOffset() + 8,
//                            element.getTextRange().getEndOffset() - 1);
//                    Annotation annotation = holder.createInfoAnnotation(range, null);
//                    annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
//                } else if (properties.size() == 0) {
//                    TextRange range = new TextRange(element.getTextRange().getStartOffset() + 8,
//                            element.getTextRange().getEndOffset() - 1);
//                    holder.createErrorAnnotation(range, "Unresolved property");
//                }
//            }
//        }
//    }
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        if (!(element instanceof PsiLiteralExpression)) return;

        // Ensure the Psi element contains a string that starts with the key and separator
        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if ((value == null) || !value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR)) return;

        // Define the text ranges (start is inclusive, end is exclusive)
        // "simple:key"
        //  01234567890
        TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset(), SIMPLE_PREFIX_STR.length() + 1);
        TextRange separatorRange = TextRange.from(prefixRange.getEndOffset(), SIMPLE_SEPARATOR_STR.length());
        TextRange keyRange = new TextRange(separatorRange.getEndOffset(), element.getTextRange().getEndOffset() - 1);

        // Get the list of properties from the Project
        String possibleProperties = value.substring(SIMPLE_PREFIX_STR.length() + SIMPLE_SEPARATOR_STR.length());
        Project project = element.getProject();
        List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project, possibleProperties);

        // Set the annotations using the text ranges.
        Annotation keyAnnotation = holder.createInfoAnnotation(prefixRange, null);
        keyAnnotation.setTextAttributes(DefaultLanguageHighlighterColors.KEYWORD);
        Annotation separatorAnnotation = holder.createInfoAnnotation(separatorRange, null);
        separatorAnnotation.setTextAttributes(SystemVerilogSyntaxHighLigther.SEPARATOR);
        if (properties.isEmpty()) {
            // No well-formed property found following the key-separator
            Annotation badProperty = holder.createErrorAnnotation(keyRange, "Unresolved property");
            badProperty.setTextAttributes(SystemVerilogSyntaxHighLigther.BAD_CHARACTER);
            // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
            badProperty.registerFix(new SystemVerilogCreatePropertyQuickFix(possibleProperties));
        } else {
            // Found at least one property
            Annotation annotation = holder.createInfoAnnotation(keyRange, null);
            annotation.setTextAttributes(SystemVerilogSyntaxHighLigther.VALUE);
        }
    }

}
