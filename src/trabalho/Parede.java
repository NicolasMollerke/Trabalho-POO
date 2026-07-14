/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import trabalho.modelo.Elemento;

/**
 *
 * @author nicol
 */
public class Parede extends Elemento{
    
    public Parede (int i, int j) {
        super(i,j);
    }
    
    public String getSimbolo() { 
        return "#"; 
    }
}
