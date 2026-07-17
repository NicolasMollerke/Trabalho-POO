/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import java.util.Random;
import trabalho.GerenciadorMovimento;
import trabalho.Movel;
import trabalho.modelo.ElementoDinamico;
import trabalho.JanelaJogo;

/**
 *
 * @author nicol
 */
public class Velociraptor extends Dinossauro implements Movel, Runnable{
    
    
    
    public Velociraptor (int i, int j) {
        super(2, i, j, "Velociraptor");
        
    }

    public int[] mover(){
        Random gerador = new Random();
       
        int direcao = gerador.nextInt(4) + 1;
                
        int linhaNova = this.linha;
        int colunaNova = this.coluna;
        int[] coordenadas = new int[2]; 
                
        if (direcao == 1) linhaNova--; //Cima
        else if (direcao == 2) colunaNova--; // Esquerda
        else if (direcao == 3) colunaNova++; // Direita
        else if (direcao == 4) linhaNova++;  // Baixo
        
        coordenadas[0] = linhaNova;
        coordenadas[1] = colunaNova;
        
        return coordenadas;
    }
    
    public int atacar(ElementoDinamico personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "V"; 
    }
    
@Override
    public void run() {
        while (this.estaVivo()) { 
            try {
                while (JanelaJogo.isJogoPausado()) {
                    Thread.sleep(500); 
                }
                
               if (!rodando || !this.estaVivo()) 
                   break;

                Thread.sleep(1500); // Intervalo de ação do Velociraptor
                
                // O Velociraptor chama o movimento DUAS vezes por ciclo
                if (gerenciador != null) {
                    gerenciador.realizarMovimento(this);
                    // Checa novamente se o jogo não pausou por conta do primeiro passo (ex: iniciou combate)
                    if (!JanelaJogo.isJogoPausado() && this.estaVivo()) {
                        gerenciador.realizarMovimento(this);
                    }
                }
                
            } catch (InterruptedException e) {
                System.out.println("Thread do Velociraptor interrompida.");
            }
        }
    }
}

