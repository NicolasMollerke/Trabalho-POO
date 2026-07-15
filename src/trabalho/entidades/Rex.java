/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import trabalho.modelo.ElementoDinamico;

/**
 *
 * @author nicol
 */
public class Rex extends Dinossauro{
    public Rex (int i, int j) {
        super(3, i, j, "Rex");
    }
    
    public int atacar(ElementoDinamico personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "R"; 
    }
}
