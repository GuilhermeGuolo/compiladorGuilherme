/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorguilherme;

/**
 *
 * @author comp15
 */
public class ErroLexico extends Exception{

    public ErroLexico(String message) {
        super("Erro lexico "+message);
    }  
}
