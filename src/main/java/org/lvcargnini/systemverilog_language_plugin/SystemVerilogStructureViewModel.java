package org.lvcargnini.systemverilog_language_plugin;
import com.intellij.ide.structureView.*;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogFile;
import org.jetbrains.annotations.NotNull;

public class SystemVerilogStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {
    public SystemVerilogStructureViewModel(PsiFile psiFile) {
        super(psiFile, new SystemVerilogStructureViewElement(psiFile));
    }
    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }
    
    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof SystemVerilogFile;
    }
}
