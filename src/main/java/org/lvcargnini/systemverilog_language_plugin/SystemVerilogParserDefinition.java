package org.lvcargnini.systemverilogplugin;
import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import org.lvcargnini.systemverilogplugin.parser.SystemVerilogParser;
import org.lvcargnini.systemverilogplugin.psi.*;
import org.jetbrains.annotations.NotNull;

public class SystemVerilogParserDefinition implements ParserDefinition {
        public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
        public static final TokenSet COMMENTS = TokenSet.create(SystemVerilogTypes.COMMENT);

        public static final IFileElementType FILE = new IFileElementType(SystemVerilogLanguage.INSTANCE);

        @NotNull
        @Override
        public Lexer createLexer(Project project) {
            return new SystemVerilogLexerAdapter();
        }

        @NotNull
        public TokenSet getWhitespaceTokens() {
            return WHITE_SPACES;
        }

        @NotNull
        @Override
        public TokenSet getCommentTokens() {
            return COMMENTS;
        }

        @NotNull
        @Override
        public TokenSet getStringLiteralElements() {
            return TokenSet.EMPTY;
        }

        @NotNull
        @Override
        public PsiParser createParser(final Project project) {
            return new SystemVerilogParser();
        }

        @Override
        public IFileElementType getFileNodeType() {
            return FILE;
        }

        @Override
        public PsiFile createFile(FileViewProvider viewProvider) {
            return new SystemVerilogFile(viewProvider);
        }

        @Override
        public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
            return SpaceRequirements.MAY;
        }

        @NotNull
        @Override
        public PsiElement createElement(ASTNode node) {
            return SystemVerilogTypes.Factory.createElement(node);
        }

}
