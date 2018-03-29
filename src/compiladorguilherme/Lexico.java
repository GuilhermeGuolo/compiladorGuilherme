/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorguilherme;

import java.util.Stack;

/**
 *
 * @author Guilherme
 */
public class Lexico {

    Stack<Token> tk;
    MapaTokens mapaTok = new MapaTokens();

    public Lexico() {
        tk = new Stack<>();
    }

    public Stack<Token> analiseLexica(Stack<Character> pilha) throws ErroLexico {
        String palavra = "";
        while (!pilha.isEmpty()) {
            Character caractere = pilha.pop();
            if (Character.isWhitespace(caractere)) {
                continue;
            }
//          analisar letras/digitos
            if (Character.isLetter(caractere) || caractere == '_') {
                pilha = verificaPalavras(pilha, caractere);
//              analisar digitos
            } else if (Character.isDigit(caractere)) {
                pilha = verificaInteiros(pilha, caractere);
            } else if (operadoresMatematicos(caractere)) {
                tk.push(mapaTok.getToken(Character.toString(caractere)));
            } //analisar atribuicao
            else if (caractere == ':') {
                verificaAtribuicao(pilha, caractere);
            } else if (operadoresRelacionais(caractere)) {
                verificaRelacionais(pilha, caractere);
            } else if (caractere == '\'') {
                verificaLiteral(pilha, caractere);
            } else if (Simbolos(caractere)) {
                verificaSimbolos(pilha, caractere);

            }

        }
        return tk;
    }

    public boolean operadoresMatematicos(Character caractere) {
        //if(caractere=='+'||caractere=='-'||caractere=='*'||caractere=='/')
        return caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/';

        //return caractere;
    }

    public boolean operadoresRelacionais(Character caractere) {
        return caractere == '>' || caractere == '<' || caractere == '=';
    }

    public boolean Simbolos(Character caractere) {
        return caractere == '.' || caractere == ',' || caractere == ';' || caractere == '$'
                || caractere == '[' || caractere == ']' || caractere == '('
                || caractere == ')';
    }

    public Stack<Character> verificaPalavras(Stack<Character> pilha, Character caractere) throws ErroLexico {

        String palavra = caractere.toString();
        while (!pilha.empty()) {

            caractere = pilha.pop();
            if (palavra.length() > 30) {
                throw new ErroLexico("ERRO LEXICO: " + palavra);
            }

            if (Character.isLetterOrDigit(caractere) || caractere.equals('_')) {
                palavra += caractere;
            } else {
                pilha.push(caractere);
                break;
            }
        }
        tk.add(mapaTok.getToken(palavra));

        return pilha;
    }

    public Stack<Character> verificaInteiros(Stack<Character> pilha, Character caractere) throws ErroLexico {
        String palavra = caractere.toString();
        while (!pilha.empty()) {
            caractere = pilha.pop();

            if (Integer.parseInt(palavra) > 32767) {
                throw new ErroLexico("");

            }

            if (Character.isLetter(caractere)) { //tem que adicionar exception

                break;
            } else if (!Character.isDigit(caractere)) {
                pilha.push(caractere);
                break;

            } else {
                palavra += caractere;

            }
        }
        tk.add(mapaTok.getToken(palavra));

        return pilha;
    }

    public void verificaAtribuicao(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();

        if (pilha.empty()) {
            tk.add(mapaTok.getToken(palavra));
            return;
        }
        caractere = pilha.pop();
        if (caractere == '=') {
            palavra += caractere;
            tk.add(mapaTok.getToken(palavra));
            return;
        }
        pilha.push(caractere);
        tk.add(mapaTok.getToken(palavra));
    }

    public void verificaRelacionais(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();

        if (pilha.empty()) {
            tk.add(mapaTok.getToken(palavra));
            return;
        }
        if (caractere == '>') {

            caractere = pilha.pop();

            if (caractere == '=') {
                palavra += caractere.toString();
                tk.add(mapaTok.getToken(palavra));
                // palavra = "";

            } else {
                pilha.push(caractere);
                tk.add(mapaTok.getToken(palavra));

            }

        } else if (caractere == '<') {
            caractere = pilha.pop();
            if (null == caractere) {
                pilha.push(caractere);

            } else {
                switch (caractere) {
                    case '>':
                        palavra += caractere.toString();
                        tk.add(mapaTok.getToken(palavra));
                        break;
                    case '=':
                        palavra += caractere.toString();
                        tk.add(mapaTok.getToken(palavra));
                        break;
                    default:
                        pilha.push(caractere);
                        break;
                }
            }

        }
    }

    public void verificaComentario(Stack<Character> pilha, Character caractere) {

    } //nao feito ainda

    public void verificaLiteral(Stack<Character> pilha, Character caractere) throws ErroLexico {
        String palavra = "";

        while (!pilha.empty()) {
            caractere = pilha.pop();
            if (caractere == '\'') {
                if (palavra.length() > 255) {
                    throw new ErroLexico("erro");

                }

                tk.add(new Token(mapaTok.getCodigo("Literal"), palavra));
                return;
            } else {
                palavra += caractere.toString();
            }

        }
        if (pilha.empty() && caractere != '\'') {
            throw new ErroLexico("Literal nao fechado");
        }

    }

    public void verificaSimbolos(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();

        if (caractere == '.' || caractere == '(') {
            if (pilha.empty()) {
                tk.add(mapaTok.getToken(palavra));
                return;
            }
            if (caractere == '.') {

                caractere = pilha.pop();

                if (caractere == '.') {
                    palavra += caractere.toString();
                    tk.add(mapaTok.getToken(palavra));

                } else {
                    pilha.push(caractere);
                    tk.add(mapaTok.getToken(palavra));

                }

            } else if (caractere == '(') {
                if (!pilha.empty()) {
                    caractere = pilha.pop();
                    if (caractere == '*') {
                        verificaComentario(pilha, caractere);
                    }
                } else {
                    tk.add(mapaTok.getToken(palavra));
                    return;
                }
            }
        } else {
            tk.add(mapaTok.getToken(palavra));
            return;
        }

        /*if (pilha.empty()) {
            tk.add(mapaTok.getToken(palavra));
            return;
        }

        if (caractere == '.') {

            caractere = pilha.pop();

            if (caractere == '.') {
                palavra += caractere.toString();
                tk.add(mapaTok.getToken(palavra));

            } else {
                pilha.push(caractere);
                tk.add(mapaTok.getToken(palavra));

            }

        } else if (caractere == '(') {
            if (!pilha.empty()) {
                caractere = pilha.pop();
                if (caractere == '*') {
                    verificaComentario(pilha, caractere);
                }
            } else {
                tk.add(mapaTok.getToken(palavra));
                return;
            }
        }*/
    }

}
