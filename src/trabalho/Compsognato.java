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
public class Compsognato extends Dinossauro implements Movel{
    public Compsognato (int i, int j) {
        super(1, i, j);
    }

    public void mover(Tabuleiro tabuleiro){
        Random gerador = new Random();
        Elemento[][] matriz = tabuleiro.getMatriz();
        
        

        boolean mover = false;
            
        while (!mover) {
            int direcao = gerador.nextInt(4) + 1;
                
            int linhaNova = this.linha;
            int colunaNova = this.coluna;
                
            if (direcao == 1) linhaNova--; //Cima
            else if (direcao == 2) colunaNova--; // Esquerda
            else if (direcao == 3) colunaNova++; // Direita
            else if (direcao == 4) linhaNova++;  // Baixo
                
            if (linhaNova >= 0 && linhaNova < tabuleiro.getTamanho() && colunaNova >=0 && colunaNova < tabuleiro.getTamanho()){
                if (matriz[linhaNova][colunaNova] == null) {
                    matriz[this.linha][this.coluna] = null;
                    matriz[linhaNova][colunaNova] = this;
                        
                    this.linha = linhaNova;
                    this.coluna = colunaNova;
                        
                    mover = true;
                }
            }
        }
    }
    
    public int atacar(Personagem personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "C"; 
    }
}
