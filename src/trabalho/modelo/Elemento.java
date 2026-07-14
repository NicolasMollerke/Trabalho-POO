/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.modelo;

/**
 *
 * @author nicol
 */
public abstract class Elemento {
    protected int linha;
    protected int coluna;
    
    public abstract String getSimbolo();
    
    public Elemento (int i, int j) {
        linha = i;
        coluna = j;
    }
}
