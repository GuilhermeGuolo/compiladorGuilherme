/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.Estruturas;

/**
 *
 * @author Guilherme
 */
public class Token {
    private final int codigo;
    private final String palavra;
    private final int linha;

    public Token(int codigo, String palavra,int linha) {
        this.codigo = codigo;
        this.palavra = palavra;
        this.linha = linha;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getPalavra() {
        return palavra;
    }
    
    public int getLinha(){
        return linha;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Palavra: " + palavra;
    }

    
}
