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
public class Personagem extends Elemento implements Movel{
    private int percepcao;
    private int saude;
    private Arma arma;
    private Bastao bastao;
    private Kit kit;
    
    public Personagem (int percepcao) {
        super(0,0);
        this.percepcao = percepcao;
        this.saude = 5;
        this.arma = null;
        this.bastao = null;
        this.kit = null;
    }
    
    public String getSimbolo() { 
        return "P"; 
    }
    
    public void  setPosicao(int i, int j){
        super.linha = i;
        super.coluna = j;
    }
    
    public void mover(Tabuleiro tabuleiro) {
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
                
            switch (direcao) {
                case 1:
                    linhaNova--; //Cima
                    break;
                case 2:
                    colunaNova--; // Esquerda
                    break;
                case 3:
                    colunaNova++; // Direita
                    break;
                case 4:
                    linhaNova++;  // Baixo
                    break;
                default:
                    break;
            }
                
            if (linhaNova >= 0 && linhaNova < tabuleiro.getTamanho() && colunaNova >=0 && colunaNova < tabuleiro.getTamanho()){
                Elemento destino = matriz[linhaNova][colunaNova];
                
                if (destino == null) {
                    matriz[this.linha][this.coluna] = null;
                    matriz[linhaNova][colunaNova] = this;
                        
                    this.linha = linhaNova;
                    this.coluna = colunaNova;
                        
                    mover = true;
                }else if (destino instanceof Caixa) {
                    System.out.println("\n[!] Você pisou em uma caixa de suprimentos 'X'!");
                
                    Caixa caixa = (Caixa) destino;
                
                    Elemento itemSurpresa = caixa.abrirCaixa();
                
                    if (itemSurpresa instanceof Dinossauro) {
                        System.out.println("Cuidado, um Compsognato saiu da caixa!");
                        matriz[linhaNova][colunaNova] = itemSurpresa;
                        
                        mover = true;
                    } else {
                        if (itemSurpresa instanceof Arma) {
                            this.arma = (Arma) itemSurpresa;
                            System.out.println("Parabéns! Você adquiriu uma Arma de Dardos!");
                        }
                        if (itemSurpresa instanceof Bastao){
                            this.bastao = (Bastao) itemSurpresa;
                            System.out.println("Parabéns! Você adquiriu um Bastão de Choque");
                        } 
                        if (itemSurpresa instanceof Kit){
                            this.kit = (Kit) itemSurpresa;
                            System.out.println("Parabéns! Você adquiriu um Kit Médico");
                        }
                        
                        matriz[this.linha][this.coluna] = null; // esvazia onde o P estava
                        matriz[linhaNova][colunaNova] = this;   // coloca o P onde a caixa estava
                        
                        this.linha = linhaNova;
                        this.coluna = colunaNova;
                        
                        mover = true;
                    }   
                }
                else {
                    System.out.println("Caminho bloqueado por uma parede!");
                }
            } else {
                System.out.println("Limite do mapa atingido");
            }
        }
    }
}
    
