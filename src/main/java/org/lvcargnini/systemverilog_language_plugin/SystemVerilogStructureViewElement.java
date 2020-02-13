package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogFile;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogProperty;
import org.lvcargnini.systemverilog_language_plugin.psi.impl.SystemVerilogPropertyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SystemVerilogStructureViewElement implements StructureViewTreeElement, SortableTreeElement  {

    private NavigatablePsiElement myElement;

    public SystemVerilogStructureViewElement(NavigatablePsiElement element) {
        this.myElement = element;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        myElement.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return myElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return myElement.canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = myElement.getName();
        return name != null ? name : "";
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        ItemPresentation presentation = myElement.getPresentation();
        return presentation != null ? presentation : new PresentationData();
    }

    @Override
    public TreeElement[] getChildren() {
        if (myElement instanceof SystemVerilogFile) {
            SystemVerilogProperty[] properties = PsiTreeUtil.getChildrenOfType(myElement, SystemVerilogProperty.class);
            List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.length);
            for (SystemVerilogProperty property : properties) {
                treeElements.add(new SystemVerilogStructureViewElement((SystemVerilogPropertyImpl) property));
            }
            return treeElements.toArray(new TreeElement[treeElements.size()]);
        }
        return EMPTY_ARRAY;
    }

}
