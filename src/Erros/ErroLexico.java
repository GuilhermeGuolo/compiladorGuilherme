/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Erros;

import javax.swing.JOptionPane;


/**
 *
 * @author comp15
 */
public class ErroLexico extends Exception{
    
    public ErroLexico(String message,int linha) {
        
        super("Erro lexico: "+message+"Na linha: "+linha);
        JOptionPane.showMessageDialog(null,"Erro lexico: "+message+"Na linha: "+linha);
        
    }  
}
