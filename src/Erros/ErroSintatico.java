/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Erros;

import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class ErroSintatico extends Exception{

    public ErroSintatico(String message) {
        super("Erro sintatico: " + message);
        JOptionPane.showMessageDialog(null, "Erro sintatico: " + message);
    }

}
