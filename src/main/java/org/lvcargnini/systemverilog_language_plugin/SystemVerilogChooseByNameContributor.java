package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.navigation.*;
import com.intellij.openapi.project.Project;
//import com.intellij.sdk.language.psi.SystemVerilogProperty;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SystemVerilogChooseByNameContributor implements ChooseByNameContributor  {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project);
        List<String> names = new ArrayList<String>(properties.size());
        for (SystemVerilogProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                names.add(property.getKey());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // TODO: include non project items
        List<SystemVerilogProperty> properties = SystemVerilogUtil.findProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}
