/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Velociraptor extends Dinossauro{
    public Velociraptor () {
        super(2);
    }

    @Override
    public boolean podeSerAtingidoPorDardo(){
        return false;
    }

    public void mover(Tabuleiro tabuleiro, Personagem personagem){

    }
    
    public String getSimbolo() { 
        return "V"; 
    }
}
