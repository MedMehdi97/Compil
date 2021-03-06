package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaire = \/\/.*\n?
operateurB = [\*\/]
operateurCompA = (<|>)


%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }
"entier"               { return symbol(CodesLexicaux.ENTIER); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"lire"               { return symbol(CodesLexicaux.LIRE); }
"si"			       { return symbol(CodesLexicaux.SI); }
"alors"			       { return symbol(CodesLexicaux.ALORS); }
"sinon"			       { return symbol(CodesLexicaux.SINON); }
"finsi"		           { return symbol(CodesLexicaux.FINSI); }
"non"                  { return symbol(CodesLexicaux.NON); }
"tantque"              { return symbol(CodesLexicaux.TANTQUE); }
"repeter"              { return symbol(CodesLexicaux.REPETER); }
"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }
"fonction"             { return symbol(CodesLexicaux.FONCTION); }
"retourne"             { return symbol(CodesLexicaux.RETOURNE); }
"longueur"             { return symbol(CodesLexicaux.LONGUEUR); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
"="                    { return symbol(CodesLexicaux.EGALE); }
"+"                    { return symbol(CodesLexicaux.PLUS); }
"-"                    { return symbol(CodesLexicaux.MOINS); }

"et"                   { return symbol(CodesLexicaux.OPERET, yytext()); }
"ou"                   { return symbol(CodesLexicaux.OPEROU, yytext()); }
"="                    { return symbol(CodesLexicaux.EGALE); }
"=="                   { return symbol(CodesLexicaux.OPEREGALE); }
"!="                   { return symbol(CodesLexicaux.OPERNEGALE); }

"("                    { return symbol(CodesLexicaux.PAROUVRANTE); }
")"                    { return symbol(CodesLexicaux.PARFERMANTE); }

"["                    { return symbol(CodesLexicaux.CROUVRANT); }
"]"                    { return symbol(CodesLexicaux.CRFERMANT); }

","                    { return symbol(CodesLexicaux.VIRGULE); }
"."                    { return symbol(CodesLexicaux.POINT); }

{operateurCompA}        { return symbol(CodesLexicaux.OPERLOGIQUEA, yytext()); }



{operateurB}           { return symbol(CodesLexicaux.OPERB, yytext()); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               { }
{commentaire}      { }

.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }