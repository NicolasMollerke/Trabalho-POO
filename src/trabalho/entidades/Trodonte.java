/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import trabalho.Movel;
import trabalho.modelo.ElementoDinamico;
import trabalho.GerenciadorMovimento;
import trabalho.JanelaJogo;
/**
 *
 * @author nicol
 */
public class Trodonte extends Dinossauro implements Movel, Runnable{
    private Personagem personagem;
    
    
    public Trodonte (int i, int j, Personagem personagem) {
        super(2, i, j, "Trodonte");
        this.personagem = personagem;
        
    }

public int[] mover() {
        int diferencaLinha = personagem.getLinha() - this.linha;
        int diferencaColuna = personagem.getColuna() - this.coluna;
        
        int coordenadas[] = new int[2];
        
        int linhaNova = this.linha;
        int colunaNova = this.coluna;

        if (Math.abs(diferencaColuna) > Math.abs(diferencaLinha)) {
            if (diferencaColuna > 0) {
                colunaNova++;
            } else if (diferencaColuna < 0) {
                colunaNova--;
            }
        } 
        else {
            if (diferencaLinha > 0) {
                linhaNova++;
            } else if (diferencaLinha < 0) {
                linhaNova--;
            }
        }
        
        coordenadas[0] = linhaNova;
        coordenadas[1] = colunaNova;
                
        return coordenadas;
    }
    
    public int atacar(ElementoDinamico personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "T"; 
    }
    
    @Override
    public void run() {
        // O loop principal depende exclusivamente da saúde do dinossauro (seu método)
        while (this.estaVivo()) { 
            try {
                // 1. Se o jogo estiver pausado (combate rolando), a thread "dorme" e não faz nada
                while (JanelaJogo.isJogoPausado()) {
                    Thread.sleep(500); // Checa a cada meio segundo se o pause acabou
                }
                
                // 2. Checagem extra: o dinossauro pode ter morrido durante o pause (se foi o alvo do combate)
                if (!rodando || !this.estaVivo()) 
                    break;

                // 3. Pausa exigida pelo trabalho para simular o intervalo entre as ações dos inimigos[cite: 1]
                Thread.sleep(1500); // Tempo do passo (ajuste por dinossauro)
                
                // 4. Efetua o movimento
                if (gerenciador != null) {
                    gerenciador.realizarMovimento(this);
                }
                
            } catch (InterruptedException e) {
                // O uso de bloco try/catch satisfaz o requisito do trabalho de fazer uso de exceção[cite: 1]
                System.out.println("A thread do " + this.getNome() + " foi interrompida.");
            }
        }
        
        // Se saiu do while, significa que estaVivo() retornou false.
        System.out.println("💀 Thread finalizada: " + this.getNome() + " foi de arrasta pra cima.");
    }
}
