/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorguilherme;

import compilador.Estruturas.Token;
import compilador.View.Editor;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class Compilador {
    Editor editor = new Editor();
    

    /**
     */
    public static Stack<Token> rodarLexico(String texto) {

        //String texto = "1 1 1 1";

        //String palavra = "";
        Stack<Character> pilha;
        Stack<Token> pilhaToken = new Stack<>();

        try {
            pilhaToken = new Lexico().analiseLexica(MontaPilha(texto));
        } catch (ErroLexico ex) {
            System.out.println(ex.getMessage());
        }
        //pilha = MontaPilha(texto);

        Stack<Token> pilhaimprime = new Stack<>();
        for (int i = pilhaToken.size() - 1; i >= 0; i--) {
            pilhaimprime.push(pilhaToken.elementAt(i));
        }
        
        

        while (!pilhaimprime.isEmpty()) {
            System.out.println(pilhaimprime.pop());

        }
        return pilhaToken;

    }

    public static Stack<Character> MontaPilha(String texto) {
        Stack<Character> pilha = new Stack<>();
        for (int i = texto.length() - 1; i >= 0; i--) {
            if (texto.charAt(i) == '\n') {

            }
            pilha.push(texto.charAt(i));

        }

        return pilha;
    }

}
