/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class Personagem extends Elemento{
    private int percepcao;
    private int saude;
    private Arma arma;
    private Bastao bastao;
    
    public Personagem (int percepcao) {
        super(0,0);
        this.percepcao = percepcao;
        this.saude = 5;
        this.arma = null;
        this.bastao = null;
    }
    
    public String getSimbolo() { 
        return "P"; 
    }
    
    public void  setPosicao(int i, int j){
        super.linha = i;
        super.coluna = j;
    }
    
    public void moverJogador (Tabuleiro tabuleiro) {
        Scanner teclado = new Scanner(System.in);
        Elemento[][] matriz = tabuleiro.getMatriz();
        boolean mover = false;
            
        while (!mover) {    

            System.out.println("Para onde voce quer se mover?");
            System.out.println("1.^ Cima");
            System.out.println("2.< Esquerda");
            System.out.println("3.> Direita");
            System.out.println("4.v Baixo"); 
            
            int direcao = teclado.nextInt();
            
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
}
    
