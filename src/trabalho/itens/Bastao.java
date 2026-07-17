/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.itens;

import trabalho.interfaces.Ataque;
import trabalho.interfaceGrafica.JanelaJogo;
import trabalho.modelo.ElementoDinamico;

/**
 *
 * @author nicol
 */
public class Bastao extends Item implements Ataque{
    public Bastao () {
        super("bastao");
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
    
    
    public int atacar(ElementoDinamico alvo) {
        java.util.Random gerador = new java.util.Random();
        int dado = gerador.nextInt(6) + 1;
        int dano=0;
        
        if (dado == 1) {
            JanelaJogo.log("Voce errou o ataque");
        } else if (dado == 5 || dado == 6) {
            JanelaJogo.log("Voce deu um golpe critico! O dinossauro recebeu 2 de dano!");
            dano = 2;
        } else {
            JanelaJogo.log("Voce acertou o ataque! O dinossauro recebeu 1 de dano!");
            dano = 1;
        } 
        
        return dano;
    }
}
