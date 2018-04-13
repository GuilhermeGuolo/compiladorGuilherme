/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.Controle;

import compilador.Estruturas.Token;
import compiladorguilherme.Compilador;
import compilador.View.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.function.Consumer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class ControleEditor implements ActionListener {

    ModeloTabelaToken modelo = new ModeloTabelaToken();

    private final Editor editor;
    private String caminho_arquivo = null;

    public ControleEditor(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            verificarComando(e);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        }
    }

    public void verificarComando(ActionEvent ae) throws IOException {
        switch (ae.getActionCommand()) {
            case "Abrir":
                abrir();
                break;
            case "Salvar":
                salvarArquivo();
                break;
            case "Fechar":
                sair();
                break;
            case "Compila":
                Stack<Token> pilha = Compilador.rodarLexico(editor.getTexto());
                editor.limpaTabela();//nao funciona
                editor.addTabela(pilha);

                break;

        }

    }

    private void sair() {
        editor.dispose();
    }

    private void abrir() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        caminho_arquivo = fileChooser.getSelectedFile().getAbsolutePath();
        editor.setTexto(verificaArquivo(caminho_arquivo));
    }

    private void salvarArquivo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        caminho_arquivo = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
        gravar(editor.getTexto(), caminho_arquivo);
    }

    public void gravar(String texto, String caminho) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            writer.write(texto);
        } catch (IOException ex) {
            throw new IOException("Não foi possível salvar o arquivo.");
        }
    }

    public static String verificaArquivo(String caminho) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho));) {
            String linha;
            StringBuilder texto = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                texto.append(linha).append('\n');
            }
            return texto.toString();
        } catch (IOException e) {
            throw new IOException("Arquivo inexistente!");
        }
    }

}
