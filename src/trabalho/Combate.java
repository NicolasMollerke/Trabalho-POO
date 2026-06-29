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
            System.out.println("O que você vai fazer?");
            int saudeJogador = personagem.getSaude();
            int saudeDinossauro = dinossauro.getSaude();
            
            System.out.println("Saúde do Jogador: " + saudeJogador);
            System.out.println("Saúde do Dinossauro: " + saudeDinossauro);
            
            System.out.println("1. Mover para outra casa");
            System.out.println("2. Lutar com Dinossauro");

            int opcao = teclado.nextInt();
            boolean bastao = personagem.verificaBastao();
            boolean arma = personagem.verificaArma();
            
            if (opcao == 1){
                personagem.mover(tabuleiro);
                combate = false;
            } else if (opcao == 2) {
                int armaEscolhida = 0;
                
                if (bastao && arma && personagem.getArma().getMunicao() > 0) {
                    System.out.println("Selecione sua arma");
                    System.out.println("1. Bastão Elétrico");
                    System.out.println("2. Arma de Dardos");

                    armaEscolhida = teclado.nextInt();
                } 
                else if (arma && personagem.getArma().getMunicao() > 0) {
                    System.out.println("Selecione sua ação de ataque");
                    System.out.println("2. Arma de Dardos");
                    System.out.println("3. Atacar com as Mãos (Soco)");

                    armaEscolhida = teclado.nextInt();
                } 
                else if (bastao) {
                    armaEscolhida = 1;
                } 
                else {
                    System.out.println("[!] Você não tem armas! Vai ter que lutar no soco!");
                    armaEscolhida = 3;
                }
                
                if (armaEscolhida == 1) {
                    this.atacarBastao();
                } else if (armaEscolhida == 2) {
                    this.atacarArmadeDardo();
                } else if (armaEscolhida == 3) {
                    this.atacarMao(); // Certifique-se de criar o método public void atacarMao() nesta classe
                }
                
                if (dinossauro.estaVivo()) {
                    int dano = dinossauro.atacar(personagem);                     
                    personagem.levarDano(dano);
                    
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

    public void atacarBastao () {
        java.util.Random gerador = new java.util.Random();
        int dado = gerador.nextInt(6) + 1;
        
        if (dado == 1) {
            System.out.println("Você errou o ataque");
        } else if (dado == 5 || dado == 6) {
            System.out.println("Você deu um golpe crítico! O dinossauro recebeu 2 de dano!");
            dinossauro.levarDano(2);
        } else {
            dinossauro.levarDano(1);
        } 
    }
    
    public void atacarArmadeDardo () {
        if (personagem.getArma().getMunicao() >= 1) {
            if (dinossauro instanceof Velociraptor) {
                 System.out.println("Velociraptor desviou do ataque!");
            } else {
                dinossauro.levarDano(2);
            }
            personagem.getArma().gastarMunicao();
        }
       
    }
    
    public void atacarMao () {
        java.util.Random gerador = new java.util.Random();
        int dado = gerador.nextInt(6) + 1;
        
        if (!(dinossauro instanceof Rex)) {
            if (dado == 6) {
                System.out.println("Você deu um golpe crítico! O dinossauro recebeu 2 de dano!");
                dinossauro.levarDano(2);
            } else if (dado == 1 || dado == 2) {
                System.out.println("Você errou o ataque!");
            } else {
                if (dinossauro instanceof Trodonte) {
                    System.out.println("Trodonte não levou dano!");
                } else {
                    System.out.println("O dinossauro recebeu 1 de dano!");
                    dinossauro.levarDano(1);
                }
            }
        } else {
            System.out.println("Rex não leva dano sem armas!");
        }      
    }
    
    public void iniciadoPorDinossauro (Tabuleiro tabuleiro) {
        System.out.println("\n🚨! Um dinossauro atacou você de surpresa!");
        
        int dano = dinossauro.atacar(personagem);                     
        personagem.levarDano(dano);
        
        if (personagem.estaVivo()) {
            this.iniciadoPorJogador(tabuleiro);
        } else {
            System.out.println("💀 Você foi derrotado no ataque surpresa inicial... Fim de jogo!");
        }
    }
}