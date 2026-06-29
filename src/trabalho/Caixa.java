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
    
    public Elemento abrirCaixa() {
        Random gerador = new Random();
        
        while (true) {
            int item = gerador.nextInt(3) + 1;

            if (item == 1) {
                if (bastaoDisponivel) {
                    bastaoDisponivel = false;
                    return new Bastao(this.linha, this.coluna);
                }
            }else if (item == 2) {
                if (kitDisponivel) {
                    kitDisponivel = false;
                    return new Kit(this.linha, this.coluna);
                }
            } else
                return new Arma(this.linha, this.coluna);
            }
        }
    }
}
