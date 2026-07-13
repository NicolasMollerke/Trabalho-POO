/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Arma extends Item{
    private int municao;
    
    public Arma() {
        this.municao = 1;
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
    
    public int getMunicao () {
        return  municao;
    }
    
    public void ganhaMunicao () {
        this.municao += 1;
    }
    
    public void gastarMunicao() {
        this.municao -= 1;
    }
    
    public int atacar (Dinossauro alvo) {
        int dano = 0;
        if (this.municao >= 1) {
            if (alvo instanceof Velociraptor) {
                JanelaJogo.log("Velociraptor desviou do ataque!");
                this.gastarMunicao();
            } else {
                JanelaJogo.log("Voce acertou o dardo! O dinossauro recebeu 2 de dano!");
                this.gastarMunicao();
                dano = 2;
            }
        } else {
            JanelaJogo.log("Voce nao possui municao!");
        }
        
        return dano;
       
    }
}
