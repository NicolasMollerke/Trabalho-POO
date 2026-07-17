/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.mecanicas;

import trabalho.Parede;
import trabalho.interfaceGrafica.JanelaJogo;
import trabalho.interfaces.Movel;
import trabalho.itens.Item;
import trabalho.itens.Arma;
import trabalho.itens.Kit;
import trabalho.itens.Bastao;
import trabalho.itens.Inventario;
import trabalho.modelo.Elemento;
import trabalho.modelo.Tabuleiro;
import trabalho.modelo.ElementoDinamico;
import trabalho.entidades.Dinossauro;
import trabalho.entidades.Compsognato;
import trabalho.entidades.Personagem;

/**
 *
 * @author nicol
 */
public class GerenciadorMovimento {
    private Tabuleiro tabuleiro;
    private int tamanho;
    
    public GerenciadorMovimento(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.tamanho = tabuleiro.getTamanho();
    }
    
    public void processarItemCaixa(Personagem jogador, Item itemSurpresa, int novaLinha, int novaColuna, Elemento[][] matriz) {
        Inventario inventario = jogador.getInventario(); 
        
        atualizarPosicao((ElementoDinamico)jogador, novaLinha, novaColuna);


        if (itemSurpresa instanceof Arma) {
            if (inventario.getArma() == null){
                inventario.adicionarItem((Arma) itemSurpresa);
                JanelaJogo.log("Parabens! Voce adquiriu uma Arma de Dardos!");
                JanelaJogo.getInstancia().atualizarInterface();
            } else {
                inventario.getArma().ganhaMunicao();
                JanelaJogo.log("Parabens! Voce adquiriu municao para sua Arma!");
                JanelaJogo.getInstancia().atualizarInterface();
            }
                       
            
            Compsognato compso = new Compsognato(novaLinha, novaColuna);
            
            compso.setGerenciador(this);
            
            Thread threadSurpresa = new Thread(compso);
            
            tabuleiro.adicionaDinossauro(); 
            threadSurpresa.start();
            
            JanelaJogo.log("Um Compsognato surpresa atacou voce!");
            Combate combate = new Combate(jogador, compso);
            combate.iniciadoPorDinossauro(tabuleiro);
            
            
            
            matriz[novaLinha][novaColuna] = jogador;
        } else if (itemSurpresa instanceof Bastao){
            inventario.adicionarItem((Bastao) itemSurpresa);
            JanelaJogo.log("Parabens! Voce adquiriu um Bastao de Choque");
        } else if (itemSurpresa instanceof Kit){
            inventario.adicionarItem((Kit) itemSurpresa);
            JanelaJogo.log("Parabens! Voce adquiriu um Kit Medico");
        }
    }
    
    public synchronized void realizarMovimento(Movel elemento /*Elemento[][] matriz, int tamanho*/) {
        Elemento[][] matriz = tabuleiro.getMatriz();
        
        if (JanelaJogo.isJogoPausado()) {
            return; 
        }
        
        boolean mover = false;
        int[] coordenadas = null;
        int tentativas = 0; 

        while(!mover && tentativas < 10) {
            coordenadas = elemento.mover();
            mover = posicaoValida(coordenadas[0], coordenadas[1], (ElementoDinamico) elemento);
            tentativas++;
        }

        if (!mover) {
            return; 
        }
        
        
        if (JanelaJogo.isJogoPausado()) {
        return; 
        }

        int novaLinha = coordenadas[0];
        int novaColuna = coordenadas[1];

        Elemento destino = matriz[novaLinha][novaColuna];

        if (destino instanceof Personagem) {
            Personagem jogador = (Personagem) destino;
            JanelaJogo.log("\n[!] Um dinossauro emboscou voce!");

            Combate combate = new Combate(jogador, (Dinossauro) elemento);
            combate.iniciadoPorDinossauro(tabuleiro);
        } 
        else {
            atualizarPosicao((ElementoDinamico) elemento, novaLinha, novaColuna);
        }
    }
    
    public boolean posicaoValida(int x, int y, ElementoDinamico elemento) {
        if (x < 0 || x >= this.tamanho || y < 0 || y >= this.tamanho) {
            return false;
        }
        
        Elemento destino = tabuleiro.getMatriz()[x][y];
        
        if (destino instanceof Parede) {
            return false; 
        }

        if (destino instanceof Personagem) {
            return true; 
        }

        if (elemento instanceof Dinossauro) {
            return destino == null || destino instanceof Personagem;
        }

        return true;
    }
    
    public synchronized void atualizarPosicao (ElementoDinamico elemento, int x, int y) {
        Elemento[][] matriz = tabuleiro.getMatriz();
        
        matriz[elemento.getLinha()][elemento.getColuna()] = null;
        
        elemento.setPosicao(x, y);
        
        matriz[x][y] = elemento;
        
        JanelaJogo janela = JanelaJogo.getInstancia();
        if (janela != null) {
            janela.atualizarInterface();
        }
    }
    
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
