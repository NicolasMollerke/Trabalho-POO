/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import trabalho.interfaces.Movel;
import trabalho.modelo.ElementoDinamico;
import trabalho.mecanicas.GerenciadorMovimento;
import trabalho.interfaceGrafica.JanelaJogo;
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
