/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Personagem extends Elemento{
    private int percepcao;
    private Arma arma;
    private Bastao bastao;
    
    public Personagem (int percepcao) {
        this.percepcao = percepcao;
        this.arma = null;
        this.bastao = null;
    }
    
    public String getSimbolo() { 
        return "P"; 
    }
}
