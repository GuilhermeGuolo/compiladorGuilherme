/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.Controle;

import compilador.Estruturas.Token;
import java.util.Stack;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author comp15
 */
public class ModeloTabelaToken extends AbstractTableModel {

    Stack<Token> pilha =  new Stack<>();

    private final String[] colunas = {"Codigo", "Palavra"};

    public ModeloTabelaToken() {
        //this.pilha = pilha;

    }
    public ModeloTabelaToken(Stack<Token> pilha){
        this.pilha = pilha;
    }
    
    
    

    public void addRow(Token token) {
        this.pilha.add(token);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.pilha.remove(linha);
        this.fireTableRowsDeleted(linha,linha);
    }

    
    @Override
    public int getRowCount() {
        return pilha.size();

    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == 0) {
            return pilha.get(linha).getCodigo();
        } else {
            return pilha.get(linha).getPalavra();
        }
    }

}
