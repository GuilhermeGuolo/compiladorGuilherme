/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.Estruturas;

import java.util.HashMap;

/**
 *
 * @author Guilherme
 */
public class MapaPalavras {
    
      private final HashMap<String, Integer> mapa_palavras;

    public MapaPalavras() {
        mapa_palavras = new HashMap<>();
        mapa_palavras.put("PROGRAMA", 52);
        mapa_palavras.put("BLOCO", 53);
        mapa_palavras.put("DCLROT", 54);
        mapa_palavras.put("LID", 55);
        mapa_palavras.put("REPIDENT", 56);
        mapa_palavras.put("DCLCONST", 57);
        mapa_palavras.put("LDCONST", 58);
        mapa_palavras.put("DCLVAR", 59);
        mapa_palavras.put("LDVAR", 60);
        mapa_palavras.put("TIPO", 61);
        mapa_palavras.put("DCLPROC", 62);
        mapa_palavras.put("DEFPAR", 63);
        mapa_palavras.put("CORPO", 64);
        mapa_palavras.put("REPCOMANDO", 65);
        mapa_palavras.put("COMANDO", 66);
        mapa_palavras.put("RCOMID", 67);
        mapa_palavras.put("RVAR", 68);
        mapa_palavras.put("PARAMETROS", 69);
        mapa_palavras.put("REPPAR", 70);
        mapa_palavras.put("ELSEPARTE", 71);
        mapa_palavras.put("VARIAVEL", 72);
        mapa_palavras.put("VARIAVEL1", 73);
        mapa_palavras.put("REPVARIAVEL", 74);
        mapa_palavras.put("ITEMSAIDA", 75);
        mapa_palavras.put("REPITEM", 76);
        mapa_palavras.put("EXPRESSAO", 77);
        mapa_palavras.put("REPEXPSIMP", 78);
        mapa_palavras.put("EXPSIMP", 79);
        mapa_palavras.put("REPEXP", 80);
        mapa_palavras.put("TERMO", 81);
        mapa_palavras.put("REPTERMO", 82);
        mapa_palavras.put("FATOR", 83);
        mapa_palavras.put("CONDCASE", 84);
        mapa_palavras.put("CONTCASE", 85);
        mapa_palavras.put("RPINTEIRO", 86);
        mapa_palavras.put("SEM EFE", 87);
}
    
}
