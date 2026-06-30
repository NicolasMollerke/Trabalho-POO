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
            boolean bastao = personagem.verificaBastao();
            boolean arma = personagem.verificaArma();
            
            if (opcao == 1){
                personagem.mover(tabuleiro);
                combate = false;
            } else if (opcao == 2) {
                int armaEscolhida = 0;
                
                if (bastao && arma && personagem.getArma().getMunicao() > 0) {
                    System.out.println("Selecione sua arma");
                    System.out.println("1. Bastao Eletrico");
                    System.out.println("2. Arma de Dardos");

                    armaEscolhida = teclado.nextInt();
                } 
                else if (arma && personagem.getArma().getMunicao() > 0) {
                    System.out.println("Selecione sua açao de ataque");
                    System.out.println("2. Arma de Dardos");
                    System.out.println("3. Atacar com as Maos (Soco)");

                    armaEscolhida = teclado.nextInt();
                            
                } 
                else if (bastao) {
                    System.out.println("[!] Você tem um bastao! Use ele para atacar!");
                    armaEscolhida = 1;
                } 
                else {
                    System.out.println("[!] Você nao tem armas! Vai ter que lutar no soco!");
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
            System.out.println("Voce errou o ataque");
        } else if (dado == 5 || dado == 6) {
            System.out.println("Voce deu um golpe critico! O dinossauro recebeu 2 de dano!");
            dinossauro.levarDano(2);
        } else {
            dinossauro.levarDano(1);
            System.out.println("Voce acertou o ataque! O dinossauro recebeu 1 de dano!");
        } 
    }
    
    public void atacarArmadeDardo () {
        if (personagem.getArma().getMunicao() >= 1) {
            if (dinossauro instanceof Velociraptor) {
                 System.out.println("Velociraptor desviou do ataque!");
            } else {
                dinossauro.levarDano(2);
                System.out.println("Voce acertou o dardo! O dinossauro recebeu 2 de dano!");
            }
            personagem.getArma().gastarMunicao();
        }
       
    }
    
    public void atacarMao () {
        java.util.Random gerador = new java.util.Random();
        int dado = gerador.nextInt(6) + 1;
        
        if (!(dinossauro instanceof Rex)) {
            if (dado == 6) {
                System.out.println("Voce deu um golpe critico! O dinossauro recebeu 2 de dano!");
                dinossauro.levarDano(2);
            } else if (dado == 1 || dado == 2) {
                System.out.println("Voce errou o ataque!");
            } else {
                if (dinossauro instanceof Trodonte) {
                    System.out.println("Trodonte nao levou dano!");
                } else {
                    System.out.println("O dinossauro recebeu 1 de dano!");
                    dinossauro.levarDano(1);
                }
            }
        } else {
            System.out.println("Rex nao leva dano sem armas!");
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