/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabalho;

import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class Combate {
    private Personagem personagem;
    private Dinossauro dinossauro;
    
    public Combate(Personagem personagem, Dinossauro dinossauro) {
        this.personagem = personagem;
        this.dinossauro = dinossauro;
    }
    
    public void iniciadoPorJogador(Tabuleiro tabuleiro) {
        Scanner teclado = new Scanner(System.in);
        boolean combate = true;
        
        while (combate) {
            System.out.println("O que voce vai fazer?");
            int saudeJogador = personagem.getSaude();
            int saudeDinossauro = dinossauro.getSaude();
            
            System.out.println("Saude do Jogador: " + saudeJogador);
            System.out.println("Saude do Dinossauro: " + saudeDinossauro);
            
            System.out.println("1. Mover para outra casa");
            System.out.println("2. Lutar com Dinossauro");

            int opcao = teclado.nextInt();
            Bastao bastao = personagem.getInventario().getBastao();
            Arma arma = personagem.getInventario().getArma();
            
            if (opcao == 1){
                personagem.mover();
                combate = false;
            } else if (opcao == 2) {
                int armaEscolhida = 0;
                
                if (bastao != null && arma != null && arma.getMunicao() > 0) {
                    System.out.println("Selecione sua arma");
                    System.out.println("1. Bastao Eletrico");
                    System.out.println("2. Arma de Dardos");

                    armaEscolhida = teclado.nextInt();
                } 
                else if (arma != null && arma.getMunicao() > 0) {
                    System.out.println("Selecione sua açao de ataque");
                    System.out.println("2. Arma de Dardos");
                    System.out.println("3. Atacar com as Maos (Soco)");

                    armaEscolhida = teclado.nextInt();
                            
                } 
                else if (bastao != null) {
                    System.out.println("[!] Voce tem um bastao! Use ele para atacar!");
                    armaEscolhida = 1;
                } 
                else {
                    System.out.println("[!] Voce nao tem armas! Vai ter que lutar no soco!");
                    armaEscolhida = 3;
                }
                
                int dano = 0;
                
                if (armaEscolhida == 1) {
                    dano = bastao.atacar();
                } else if (armaEscolhida == 2) {
                    dano = arma.atacar(dinossauro);
                } else if (armaEscolhida == 3) {
                    dano = personagem.atacar(dinossauro);
                }
                
                dinossauro.levarDano(dano);
                
                
                if (dinossauro.estaVivo()) {
                    int danoDino = dinossauro.atacar(personagem);                     
                    personagem.levarDano(danoDino);
                    
                    if (!(personagem.estaVivo())) {
                        combate = false;
                    }
                }
                
                if (!dinossauro.estaVivo()) {
                    System.out.println("🎉 Você derrotou o dinossauro!");
                    tabuleiro.getMatriz()[dinossauro.linha][dinossauro.coluna] = null;
                    tabuleiro.removeDinossauro();
                    combate = false; 
                }               
            }
        }
    }

    public void iniciadoPorDinossauro (Tabuleiro tabuleiro) {
        System.out.println("\n🚨! Um dinossauro atacou voce de surpresa!");
        
        int dano = dinossauro.atacar(personagem);                     
        personagem.levarDano(dano);
        
        if (personagem.estaVivo()) {
            this.iniciadoPorJogador(tabuleiro);
        } else {
            System.out.println("💀 Você foi derrotado no ataque surpresa inicial... Fim de jogo!");
        }
    }
}