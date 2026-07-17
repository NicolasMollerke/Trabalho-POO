/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import java.util.Random;
import trabalho.interfaces.Movel;
import trabalho.modelo.ElementoDinamico;
import trabalho.mecanicas.GerenciadorMovimento;
import trabalho.interfaceGrafica.JanelaJogo;

/**
 *
 * @author nicol
 */
public class Compsognato extends Dinossauro implements Movel, Runnable{
    
    
    public Compsognato (int i, int j) {
        super(1, i, j, "Compsognato");
    }

    public int[] mover(){
        Random gerador = new Random();
        
        int direcao = gerador.nextInt(4) + 1;
        int[] coordenadas = new int[2]; 

                
        int linhaNova = this.linha;
        int colunaNova = this.coluna;
                
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
        return "C"; 
    }
    
    @Override
    public void run() {
        //loop principal verifica se o dinassuro esta vivo
        while (this.estaVivo()) { 
            try {
                while (JanelaJogo.isJogoPausado()) { //jogo em combate/jogaodr morre
                    Thread.sleep(500); //testa se o pause acabou
                }
                
                //ve se o dinossauro morreu no combate
                if (!rodando || !this.estaVivo()) 
                    break;

                //tempo em cada movimento de dinossauro
                Thread.sleep(1500);
                
                //faz o movimento
                if (gerenciador != null) {
                    gerenciador.realizarMovimento(this);
                }
                
            } catch (InterruptedException e) {
                System.out.println("A thread do " + this.getNome() + " foi interrompida.");
            }
        }
        
        //dinossauro morre ou o jogo reinicia
        System.out.println("💀 Thread finalizada: " + this.getNome() + " morreu");
    }
}
