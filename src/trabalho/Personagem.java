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
        System.out.println("Para onde voce quer se mover?");
        System.out.println("1.^ Cima");
        System.out.println("2.< Esquerda");
        System.out.println("3.> Direita");
        System.out.println("4.v Baixo");

        Scanner teclado = new Scanner(System.in);
        int direcao = teclado.nextInt();
        Elemento[][] matriz = tabuleiro.getMatriz();

        if (direcao == 1) {
            int linhaNova = this.linha - 1;
            if (matriz[linhaNova][this.coluna] == null){
                matriz[linhaNova][this.coluna] = this;
                matriz[this.linha][this.coluna] = null;
                setPosicao(linhaNova, this.coluna); 
            }
        }

        // 2. ESQUERDA
        if (direcao == 2) {
            int colunaNova = this.coluna - 1;
            if (matriz[this.linha][colunaNova] == null) {
                matriz[this.linha][colunaNova] = this;
                matriz[this.linha][this.coluna] = null;
                setPosicao(this.linha, colunaNova);
            }
        }

        if (direcao == 3) {
            int colunaNova = this.coluna + 1;
            if (matriz[this.linha][colunaNova] == null) {
                matriz[this.linha][colunaNova] = this;
                matriz[this.linha][this.coluna] = null;
                setPosicao(this.linha, colunaNova);
            }
        }

        if (direcao == 4) {
            int linhaNova = this.linha + 1;
            if (matriz[linhaNova][this.coluna] == null) {
                matriz[linhaNova][this.coluna] = this;
                matriz[this.linha][this.coluna] = null;
                setPosicao(linhaNova, this.coluna);
            }
        }
    }
    
}
