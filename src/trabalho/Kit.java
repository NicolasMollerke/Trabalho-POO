/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Kit extends Item {
    public Kit () {
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
    
    public int curar(Personagem personagem) {
        if (personagem.getSaude() == 5) {
            JanelaJogo.log("Você ja possui saude maxima");
            return 0;
        } else {
            JanelaJogo.log("Você Recuperou 1 de vida!");
            return 1;
        }
    }
}
