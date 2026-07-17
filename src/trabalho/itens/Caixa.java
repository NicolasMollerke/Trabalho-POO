/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.itens;

import trabalho.itens.Item;
import trabalho.itens.Arma;
import trabalho.itens.Kit;
import trabalho.itens.Bastao;
import trabalho.modelo.Tabuleiro;
import trabalho.modelo.Elemento;
import java.util.Random;

/**
 *
 * @author nicol
 */
public class Caixa extends Elemento{

    public Caixa (int i, int j) {
        super(i, j);
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
    
    public Item abrirCaixa(Tabuleiro tabuleiro) {
        String itemSorteado = tabuleiro.sortearItem();
        
        if (itemSorteado.equals("bastao")) {
            return new Bastao();
        } else if (itemSorteado.equals("kit")) {
            return new Kit();
        } else {
            return new Arma(1);
        }
    }
}
