/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorguilherme;

import Erros.ErroSintatico;
import compilador.Estruturas.MapaNaoTerminais;
import compilador.Estruturas.NaoTerminal;
import compilador.Estruturas.Token;
import compilador.Estruturas.MapaTokens;
import compilador.Estruturas.TabelaParsing;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Sintatico {

    Stack<Token> a;
    Stack<Integer> x;
    TabelaParsing tabelaparsing;

    MapaNaoTerminais mapaNaoTerminais = new MapaNaoTerminais();
    MapaTokens mapatokens = new MapaTokens();

    public Sintatico(Stack<Token> pilhatokens) {
        this.a = pilhatokens;
        this.x = new Stack<>();
        this.tabelaparsing = new TabelaParsing();
        x.push(mapaNaoTerminais.getCodigo("PROGRAMA"));

    }

    public void AnaliseSintatica() throws ErroSintatico {
        Integer simbolo;
        Token token;

        while (!a.empty()) {

            simbolo = x.peek();
            token = a.peek();

            if (simbolo < 52) {

                if (simbolo == token.getCodigo()) {
                    x.pop();
                    a.pop();
                } else {
                    throw new ErroSintatico("");
                    
                    
                }

            } else {
                String derivado = tabelaparsing.getDerivacao(simbolo, token);
                
                if (derivado == "NULL") { 
                   x.pop();
                }
                else if(derivado == null){
                    throw new ErroSintatico("Derivação nula!");
                }
                
                else {
                    
                    Derivacao(simbolo, token, derivado);
                }

            }
            

        }
        JOptionPane.showMessageDialog(null,"Sucesso!");
        System.out.println("Sucesso!");

    }

//    public void derivacao(NaoTerminal terminal, Token token) {
//        String derivado = tabelaparsing.getDerivacao(terminal, token);
//        String[] producoes = derivado.split("\\|");
//        for (int i = producoes.length - 1; i > 0; i--) {
//            x.push(mapaNaoTerminais.getNaoTerminal(producoes[i]));
//        }
//       
//    }
    public void Derivacao(Integer simbolo, Token token, String derivado) throws ErroSintatico {

        String[] producao = derivado.split("\\|");

        for (int i = producao.length - 1; i >= 0; i--) {
            if (mapaNaoTerminais.existeSimbolo(producao[i])) { //x.pop();//apaga isso
                x.push(mapaNaoTerminais.getCodigo(producao[i]));

            } else if (mapatokens.existeToken(producao[i])) { //x.pop();//apaga isso
                x.push(mapatokens.getCodigo(producao[i]));
            } else {
                throw new ErroSintatico("");
            }

        }

    }

}
