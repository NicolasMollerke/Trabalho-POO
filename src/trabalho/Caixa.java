/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.util.Random;

/**
 *
 * @author nicol
 */
public class Caixa extends Elemento{
    private boolean bastaoDisponivel;
    private boolean kitDisponivel;
    
    public Caixa (int i, int j) {
        super(i, j);
        
        this.bastaoDisponivel = true;
        this.kitDisponivel = true;
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
    
    public Elemento abrirCaixa(Tabuleiro tabuleiro) {
        String itemSorteado = tabuleiro.sortearItem();
        
        if (itemSorteado.equals("bastao")) {
            return new Bastao(this.linha, this.coluna);
        } else if (itemSorteado.equals("kit")) {
            return new Kit(this.linha, this.coluna);
        } else {
            return new Arma(this.linha, this.coluna);
        }
    }
}
