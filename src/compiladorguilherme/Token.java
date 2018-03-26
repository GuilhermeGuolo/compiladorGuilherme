/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorguilherme;

/**
 *
 * @author Guilherme
 */
public class Token {
    private final int codigo;
    private final String palavra;

    public Token(int codigo, String palavra) {
        this.codigo = codigo;
        this.palavra = palavra;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getPalavra() {
        return palavra;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Palavra: " + palavra;
    }

    
}
