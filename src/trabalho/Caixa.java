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
    private boolean armaDisponivel;
    private boolean bastaoDisponivel;
    private boolean kitDisponivel;
    private boolean dinossauroDisponivel;
    
    public Caixa (int i, int j) {
        super(i, j);
        
        this.armaDisponivel = true;
        this.bastaoDisponivel = true;
        this.kitDisponivel = true;
        this.dinossauroDisponivel = true;
    }
    
    public String getSimbolo() { 
        return "X"; 
    }
    
    public Elemento abrirCaixa() {
        Random gerador = new Random();
        
        while (true) {
            int item = gerador.nextInt(4) + 1;
        
            if (item == 1) {
                if (armaDisponivel) {
                    armaDisponivel = false;
                    return new Arma(this.linha, this.coluna);
                }
            }
            
            if (item == 2) {
                if (bastaoDisponivel) {
                    bastaoDisponivel = false;
                    return new Bastao(this.linha, this.coluna);
                }
            }
            
            if (item == 3) {
                if (kitDisponivel) {
                    kitDisponivel = false;
                    return new Kit(this.linha, this.coluna);
                }
            }
            
            if (item == 4) {
                if (dinossauroDisponivel) {
                    dinossauroDisponivel = false;
                    return new Compsognato(this.linha, this.coluna);
                }
            }
        }
    }
}
