package org.lvcargnini.systemverilogplugin.psi;

import com.intellij.psi.tree.IElementType;
import org.lvcargnini.systemverilogplugin.SystemVerilogLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SystemVerilogTokenType extends IElementType {
    public SystemVerilogTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SystemVerilogLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        final String _str = new String("SystemVerilogTokenType." + super.toString());
        return _str;
    }
}
