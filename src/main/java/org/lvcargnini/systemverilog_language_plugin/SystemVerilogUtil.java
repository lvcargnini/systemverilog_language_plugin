package org.lvcargnini.systemverilogplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.lvcargnini.systemverilogplugin.psi.*;

import java.util.*;

public class SystemVerilogUtil {
    public static List<SystemVerilogProperty> findProperties(Project project, String key) {
        List<SystemVerilogProperty> result = null;
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SystemVerilogFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SystemVerilogFile svFile = (SystemVerilogFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (svFile != null) {
                SystemVerilogProperty[] properties = PsiTreeUtil.getChildrenOfType(svFile, SystemVerilogProperty.class);
                if (properties != null) {
                    for (SystemVerilogProperty property : properties) {
                        if (key.equals(property.getKey())) {
                            if (result == null) {
                                result = new ArrayList<SystemVerilogProperty>();
                            }
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<SystemVerilogProperty>emptyList();
    }

    public static List<SystemVerilogProperty> findProperties(Project project) {
        List<SystemVerilogProperty> result = new ArrayList<SystemVerilogProperty>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(SystemVerilogFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SystemVerilogFile svFile = (SystemVerilogFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (svFile != null) {
                SystemVerilogProperty[] properties = PsiTreeUtil.getChildrenOfType(svFile, SystemVerilogProperty.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }
}
