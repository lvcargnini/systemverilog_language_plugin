package org.lvcargnini.systemverilog_language_plugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.lvcargnini.systemverilog_language_plugin.psi.SystemVerilogTypes;
import com.intellij.psi.TokenType;

@SuppressWarnings("ALL")
%%

%class SystemVerilogLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("//")[^\r\n]*
SEPARATOR=[:=]
KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "


IDENTIFIER = [:jletter:] [:jletterdigit:]*

DIGIT = [0-9]
DIGIT_OR_UNDERSCORE = [_0-9]
DIGITS = {DIGIT} | {DIGIT} {DIGIT_OR_UNDERSCORE}*
HEX_DIGIT_OR_UNDERSCORE = [_0-9A-Fa-f]

INTEGER_LITERAL = {DIGITS} | {HEX_INTEGER_LITERAL} | {BIN_INTEGER_LITERAL}
LONG_LITERAL = {INTEGER_LITERAL} [Ll]
HEX_INTEGER_LITERAL = 0 [Xx] {HEX_DIGIT_OR_UNDERSCORE}*
BIN_INTEGER_LITERAL = 0 [Bb] {DIGIT_OR_UNDERSCORE}*

FLOAT_LITERAL = ({DEC_FP_LITERAL} | {HEX_FP_LITERAL}) [Ff] | {DIGITS} [Ff]
DOUBLE_LITERAL = ({DEC_FP_LITERAL} | {HEX_FP_LITERAL}) [Dd]? | {DIGITS} [Dd]
DEC_FP_LITERAL = {DIGITS} {DEC_EXPONENT} | {DEC_SIGNIFICAND} {DEC_EXPONENT}?
DEC_SIGNIFICAND = "." {DIGITS} | {DIGITS} "." {DIGIT_OR_UNDERSCORE}*
DEC_EXPONENT = [Ee] [+-]? {DIGIT_OR_UNDERSCORE}*
HEX_FP_LITERAL = {HEX_SIGNIFICAND} {HEX_EXPONENT}
HEX_SIGNIFICAND = 0 [Xx] ({HEX_DIGIT_OR_UNDERSCORE}+ "."? | {HEX_DIGIT_OR_UNDERSCORE}* "." {HEX_DIGIT_OR_UNDERSCORE}+)
HEX_EXPONENT = [Pp] [+-]? {DIGIT_OR_UNDERSCORE}*



%state WAITING_VALUE

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return SystemVerilogTypes.COMMENT; }

<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return SystemVerilogTypes.KEY; }

<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return SystemVerilogTypes.SEPARATOR; }

<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }

<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return SystemVerilogTypes.VALUE; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

<YYINITIAL> {
  {LONG_LITERAL} { return SystemVerilogTypes.LONG_LITERAL; }
  {INTEGER_LITERAL} { return SystemVerilogTypes.INTEGER_LITERAL; }
  {FLOAT_LITERAL} { return SystemVerilogTypes.FLOAT_LITERAL; }
  {DOUBLE_LITERAL} { return SystemVerilogTypes.DOUBLE_LITERAL; }

  "true" { return SystemVerilogTypes.TRUE_KEYWORD; }
  "false" { return SystemVerilogTypes.FALSE_KEYWORD; }

  "assert" { return SystemVerilogTypes.ASSERT_KEYWORD  }
  "bool" { return SystemVerilogTypes.BOOLEAN_KEYWORD; }
  "break" { return SystemVerilogTypes.BREAK_KEYWORD; }
  "byte" { return SystemVerilogTypes.BYTE_KEYWORD; }
  "case" { return SystemVerilogTypes.CASE_KEYWORD; }
  "char" { return SystemVerilogTypes.CHAR_KEYWORD; }
  "class" { return SystemVerilogTypes.CLASS_KEYWORD; }
  "const" { return SystemVerilogTypes.CONST_KEYWORD; }
  "continue" { return SystemVerilogTypes.CONTINUE_KEYWORD; }
  "default" { return SystemVerilogTypes.DEFAULT_KEYWORD; }
  "do" { return SystemVerilogTypes.DO_KEYWORD; }
  "double" { return SystemVerilogTypes.DOUBLE_KEYWORD; }
  "else" { return SystemVerilogTypes.ELSE_KEYWORD; }
  "enum" { return SystemVerilogTypes.ENUM_KEYWORD; }
  "final" { return SystemVerilogTypes.FINAL_KEYWORD; }
  "float" { return SystemVerilogTypes.FLOAT_KEYWORD; }
  "for" { return SystemVerilogTypes.FOR_KEYWORD; }
  "goto" { return SystemVerilogTypes.GOTO_KEYWORD; }
  "if" { return SystemVerilogTypes.IF_KEYWORD; }
  "import" { return SystemVerilogTypes.IMPORT_KEYWORD; }
  "int" { return SystemVerilogTypes.INT_KEYWORD; }
  "interface" { return SystemVerilogTypes.INTERFACE_KEYWORD; }
  "long" { return SystemVerilogTypes.LONG_KEYWORD; }
  "new" { return SystemVerilogTypes.NEW_KEYWORD; }
  "package" { return SystemVerilogTypes.PACKAGE_KEYWORD; }
  "private" { return SystemVerilogTypes.PRIVATE_KEYWORD; }
  "public" { return SystemVerilogTypes.PUBLIC_KEYWORD; }
  "short" { return SystemVerilogTypes.SHORT_KEYWORD; }
  "super" { return SystemVerilogTypes.SUPER_KEYWORD; }
  "switch" { return SystemVerilogTypes.SWITCH_KEYWORD; }
  "synchronized" { return SystemVerilogTypes.SYNCHRONIZED_KEYWORD; }
  "this" { return SystemVerilogTypes.THIS_KEYWORD; }
  "throw" { return SystemVerilogTypes.THROW_KEYWORD; }
  "protected" { return SystemVerilogTypes.PROTECTED_KEYWORD; }
  "transient" { return SystemVerilogTypes.TRANSIENT_KEYWORD; }
  "return" { return SystemVerilogTypes.RETURN_KEYWORD; }
  "void" { return SystemVerilogTypes.VOID_KEYWORD; }
  "static" { return SystemVerilogTypes.STATIC_KEYWORD; }
  "strictfp" { return SystemVerilogTypes.STRICTFP_KEYWORD; }
  "while" { return SystemVerilogTypes.WHILE_KEYWORD; }
  "try" { return SystemVerilogTypes.TRY_KEYWORD; }
  "volatile" { return SystemVerilogTypes.VOLATILE_KEYWORD; }
  "throws" { return SystemVerilogTypes.THROWS_KEYWORD; }

  {IDENTIFIER} { return SystemVerilogTypes.IDENTIFIER; }

  "==" { return SystemVerilogTypes.EQEQ; }
  "!=" { return SystemVerilogTypes.NE; }
  "||" { return SystemVerilogTypes.OROR; }
  "++" { return SystemVerilogTypes.PLUSPLUS; }
  "--" { return SystemVerilogTypes.MINUSMINUS; }

  "<" { return SystemVerilogTypes.LT; }
  "<=" { return SystemVerilogTypes.LE; }
  "<<=" { return SystemVerilogTypes.LTLTEQ; }
  "<<" { return SystemVerilogTypes.LTLT; }
  ">" { return SystemVerilogTypes.GT; }
  "&" { return SystemVerilogTypes.AND; }
  "&&" { return SystemVerilogTypes.ANDAND; }

  "+=" { return SystemVerilogTypes.PLUSEQ; }
  "-=" { return SystemVerilogTypes.MINUSEQ; }
  "*=" { return SystemVerilogTypes.ASTERISKEQ; }
  "/=" { return SystemVerilogTypes.DIVEQ; }
  "&=" { return SystemVerilogTypes.ANDEQ; }
  "|=" { return SystemVerilogTypes.OREQ; }
  "^=" { return SystemVerilogTypes.XOREQ; }
  "%=" { return SystemVerilogTypes.PERCEQ; }

  "("   { return SystemVerilogTypes.LPARENTH; }
  ")"   { return SystemVerilogTypes.RPARENTH; }
  "{"   { return SystemVerilogTypes.LBRACE; }
  "}"   { return SystemVerilogTypes.RBRACE; }
  "["   { return SystemVerilogTypes.LBRACKET; }
  "]"   { return SystemVerilogTypes.RBRACKET; }
  ";"   { return SystemVerilogTypes.SEMICOLON; }
  ","   { return SystemVerilogTypes.COMMA; }
  "..." { return SystemVerilogTypes.ELLIPSIS; }
  "."   { return SystemVerilogTypes.DOT; }

  "=" { return SystemVerilogTypes.EQ; }
  "!" { return SystemVerilogTypes.EXCL; }
  "~" { return SystemVerilogTypes.TILDE; }
  "?" { return SystemVerilogTypes.QUEST; }
  ":" { return SystemVerilogTypes.COLON; }
  "+" { return SystemVerilogTypes.PLUS; }
  "-" { return SystemVerilogTypes.MINUS; }
  "*" { return SystemVerilogTypes.ASTERISK; }
  "/" { return SystemVerilogTypes.DIV; }
  "|" { return SystemVerilogTypes.OR; }
  "^" { return SystemVerilogTypes.XOR; }
  "%" { return SystemVerilogTypes.PERC; }
  "@" { return SystemVerilogTypes.AT; }

  "::" { return SystemVerilogTypes.DOUBLE_COLON; }
  "->" { return SystemVerilogTypes.ARROW; }
}

[^]                                                         { return TokenType.BAD_CHARACTER; }



