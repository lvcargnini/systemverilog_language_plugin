package org.lvcargnini.systemverilogplugin;

import com.intellij.lexer.FlexAdapter;
import java.io.Reader;

public class SystemVerilogLexerAdapter extends FlexAdapter {
    public SystemVerilogLexerAdapter() {
        super(new SystemVerilogLexer((Reader) null));
    }
}
