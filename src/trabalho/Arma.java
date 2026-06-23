/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Arma extends Elemento{
    private int municao;
    
    public Arma(int i, int j) {
        this.municao = 1;
        super(i, j);
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
}
