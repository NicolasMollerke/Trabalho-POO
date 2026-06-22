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
public class Velociraptor extends Dinossauro{
    public Velociraptor (int i, int j) {
        super(2, i, j);
    }

    public boolean podeSerAtingidoPorDardo(){
        return false;
    }

    public void mover(Tabuleiro tabuleiro){
        Random gerador = new Random();
        Elemento[][] matriz = tabuleiro.getMatriz();
        
        
        for (int i=0; i < 2; i++) {
            int direcao = gerador.nextInt(4) + 1;
            
            if (direcao == 1) {
                int linhaNova = this.linha - 1;
                if (linhaNova >= 0 && matriz[linhaNova][this.coluna] == null){
                    matriz[linhaNova][this.coluna] = this;
                    matriz[this.linha][this.coluna] = null;
                    this.linha = linhaNova;
                }
            }

            if (direcao == 2) {
                int colunaNova = this.coluna - 1;
                if (colunaNova >= 0 && matriz[this.linha][colunaNova] == null) {
                    matriz[this.linha][colunaNova] = this;
                    matriz[this.linha][this.coluna] = null;
                    this.coluna = colunaNova;
                }
            }

            if (direcao == 3) {
                int colunaNova = this.coluna + 1;
                if (colunaNova >= 0 && matriz[this.linha][colunaNova] == null) {
                    matriz[this.linha][colunaNova] = this;
                    matriz[this.linha][this.coluna] = null;
                    this.coluna = colunaNova;
                }
            }

            if (direcao == 4) {
                int linhaNova = this.linha + 1;
                if (linhaNova >= 0 && matriz[linhaNova][this.coluna] == null) {
                    matriz[linhaNova][this.coluna] = this;
                    matriz[this.linha][this.coluna] = null;
                    this.linha = linhaNova;
                }
            }
            
        }
    }
    
    public String getSimbolo() { 
        return "V"; 
    }
}
