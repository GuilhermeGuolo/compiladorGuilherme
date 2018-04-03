/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorguilherme;

import java.util.Stack;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author comp15
 */
public class ModeloTabelaToken extends AbstractTableModel {

    Stack<Token> pilha;
    
    private final String[] colunas = {"Codigo" , "Palavra"};

    public ModeloTabelaToken(Stack<Token> pilha) {
        this.pilha = pilha;
        
    }

    @Override
    public int getRowCount() {
        return pilha.size();
       
    }

    @Override
    public int getColumnCount() {
       return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
