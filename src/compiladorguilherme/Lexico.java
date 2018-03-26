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

    public Lexico() {
        tk = new Stack<>();
    }

    MapaTokens mapaTok = new MapaTokens();

    public Stack<Token> analiseLexica(Stack<Character> pilha) {
        String palavra = "";
        

        while (!pilha.isEmpty()) {
         
            Character caractere = pilha.pop();
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

                pilha = verificaAtribuicao(pilha, caractere);

            } else if (operadoresRelacionais(caractere)) {

                pilha = verificaRelacionais(pilha, caractere);
                /*while (!pilha.empty()) {
                    palavra += caractere.toString();
                    if (caractere == '>') {
                        caractere = pilha.isEmpty() ? ' ' : pilha.pop();
                        if (caractere == '=') {
                            palavra += caractere.toString();
                            tk.add(mapaTok.getToken(palavra));
                            palavra = "";
                            break;
                        } else {
                            pilha.push(caractere);
                        }
                        tk.add(mapaTok.getToken(palavra));
                        palavra = "";
                        break;
                    }
                    if (caractere == '<') {
                        caractere = pilha.isEmpty() ? ' ' : pilha.pop();
                        if (caractere == '>') {
                            palavra += caractere.toString();
                            tk.add(mapaTok.getToken(palavra));
                            palavra = "";
                            break;

                        } else if (caractere == '=') {
                            palavra += caractere.toString();
                            tk.add(mapaTok.getToken(palavra));
                            palavra = "";
                            break;
                        } else {
                            pilha.push(caractere);

                        }
                        tk.add(mapaTok.getToken(palavra));
                        palavra = "";
                        break;
                    }

                }*/
                 
            }

            if (caractere == '\'') {
                while (!pilha.empty()) {
                    caractere = pilha.pop();
                    if (caractere != '\'') {
                        palavra += caractere;

                    }

                }

            }

        }

        return tk;
    }

    public boolean operadoresMatematicos(char caractere) {
        //if(caractere=='+'||caractere=='-'||caractere=='*'||caractere=='/')
        return caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/';

        //return caractere;
    }

    public boolean operadoresRelacionais(char caractere) {
        return caractere == '>' || caractere == '<' || caractere == '=';
    }

    public Stack<Character> verificaPalavras(Stack<Character> pilha, Character caractere) {

        String palavra = caractere.toString();
        while (!pilha.empty()) {

            caractere = pilha.pop();
            //palavra+= caractere;
            if (Character.isLetterOrDigit(caractere) || caractere.equals('_')) {
                palavra += caractere;
            } else {
                break;
            }
        }
        tk.add(mapaTok.getToken(palavra));

        return pilha;
    }

    public Stack<Character> verificaInteiros(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();
        while (!pilha.empty()) {
            caractere = pilha.pop();
            //palavra += caractere;
            if (Character.isLetter(caractere)) { //tem que adicionar exception
                System.out.println("deu merda");
                break;
            } else if (!Character.isDigit(caractere)) {

                break;

            } else {
                palavra += caractere;

            }
        }
        tk.add(mapaTok.getToken(palavra));

        return pilha;
    }

    public Stack<Character> verificaAtribuicao(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();

        while (!pilha.empty()) {
            caractere = pilha.pop();
            if (caractere == '=') {
                palavra += caractere;
                tk.add(mapaTok.getToken(palavra));

                break;

            } else if (Character.isWhitespace(caractere)) {

                continue;
            } else {
                pilha.push(caractere);
            }
            tk.add(mapaTok.getToken(palavra));

            break;

        }
        if(pilha.empty()){
            tk.add(mapaTok.getToken(palavra));
        }
        
        return pilha;
    }

    public Stack<Character> verificaRelacionais(Stack<Character> pilha, Character caractere) {
        String palavra = caractere.toString();
        // ache o erro dessa PORRA
        while (!pilha.empty()) {
            caractere = pilha.pop();
            if (caractere == '>') {
                caractere =  pilha.pop();
                if (caractere == '=') {
                    palavra += caractere.toString();
                    tk.add(mapaTok.getToken(palavra));
                    palavra = "";
                    break;
                } else {
                    pilha.push(caractere);
                }
                tk.add(mapaTok.getToken(palavra));
                palavra = "";
                break;
            }
            if (caractere == '<') {
                caractere = pilha.pop();
                if (caractere == '>') {
                    palavra += caractere.toString();
                    tk.add(mapaTok.getToken(palavra));
                    palavra = "";
                    break;

                } else if (caractere == '=') {
                    palavra += caractere.toString();
                    tk.add(mapaTok.getToken(palavra));
                    palavra = "";
                    break;
                } else {
                    pilha.push(caractere);

                }
                tk.add(mapaTok.getToken(palavra));
                palavra = "";
                break;
            }

        }
        if(pilha.empty()){
            tk.add(mapaTok.getToken(palavra));
        }
       
        return pilha;
    }
}
