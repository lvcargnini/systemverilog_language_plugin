package org.lvcargnini.systemverilogplugin;


import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.lvcargnini.systemverilogplugin.*;

public class SystemVerilogSyntaxHighlighterFactory  extends SyntaxHighlighterFactory  {
    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
        return new SystemVerilogSyntaxHighLighter();
    }
}
