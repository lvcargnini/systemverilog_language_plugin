package org.lvcargnini.systemverilogplugin.psi;
import com.intellij.psi.tree.IElementType;
import org.lvcargnini.systemverilogplugin.SystemVerilogLanguage;
import org.jetbrains.annotations.*;



public class SystemVerilogElementType  extends IElementType {


    public SystemVerilogElementType(@NotNull @NonNls String debugName) {
        super(debugName, SystemVerilogLanguage.INSTANCE);
    }

}

