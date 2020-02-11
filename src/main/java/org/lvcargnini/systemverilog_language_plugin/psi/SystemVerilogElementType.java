package org.lvcargnini.systemverilog_language_plugin.psi;
import com.intellij.psi.tree.IElementType;
import org.lvcargnini.systemverilog_language_plugin.SystemVerilogLanguage;
import org.jetbrains.annotations.*;



public class SystemVerilogElementType  extends IElementType {


    public SystemVerilogElementType(@NotNull @NonNls String debugName) {
        super(debugName, SystemVerilogLanguage.INSTANCE);
    }

}

