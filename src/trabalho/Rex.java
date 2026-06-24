/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Rex extends Dinossauro{
    public Rex (int i, int j) {
        super(3, i, j);
    }

    public int getDanoAtaque(){
        return 2;
    }

    public boolean podeSerAtingidoPorMaoNua(){
        return false;
    }
    
    public int atacar(Personagem personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "R"; 
    }
}
