/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabalho.mecanicas;

import trabalho.interfaceGrafica.JanelaJogo;
import trabalho.itens.Arma;
import trabalho.itens.Bastao;
import trabalho.modelo.Tabuleiro;
import trabalho.entidades.Dinossauro;
import trabalho.entidades.Personagem;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
        JanelaJogo.setJogoPausado(true);
        boolean combate = true;
        
        while (combate) {
            int saudeJogador = personagem.getSaude();
            int saudeDinossauro = dinossauro.getSaude();
            String nomeDino = dinossauro.getNome();
            
            String status = "🩸 Saúde do Jogador: " + saudeJogador + "\n" +
                            "🦖 Saúde do " + nomeDino + ": " + saudeDinossauro + "\n\n" +
                            "O que você vai fazer?";
            
            String[] opcoesPrincipais = {"🏃 Mover/Fugir", "⚔️ Lutar com Dinossauro"};
            int escolhaPrincipal = JOptionPane.showOptionDialog(
                null, 
                status, 
                "🚨 COMBATE SURPRESA!", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.WARNING_MESSAGE, 
                null, 
                opcoesPrincipais, 
                opcoesPrincipais[1]
            );

            if (escolhaPrincipal == -1 || escolhaPrincipal == 0) {
                JanelaJogo.log("🏃 Você escolheu fugir e se mover de área!");
                combate = false;
                break;
            }

            Bastao bastao = personagem.getInventario().getBastao();
            Arma arma = personagem.getInventario().getArma();
            int armaEscolhida = 0;
            
            if (bastao != null && arma != null && arma.getMunicao() > 0) {
                String[] armas = {"🔫 Arma de Dardos","⚡ Bastão Elétrico"};
                int escolhaArma = JOptionPane.showOptionDialog(
                    null, "Selecione sua arma:", "Arsenal", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, armas, armas[0]
                );
                if (escolhaArma == 1) {
                    armaEscolhida = 1;
                } else {
                    armaEscolhida = 2; 
                }                
            } else if (arma != null && arma.getMunicao() > 0) {
                String[] armas = {"🔫 Arma de Dardos", "👊 Atacar com as Mãos (Soco)"};
                int escolhaArma = JOptionPane.showOptionDialog(
                    null, "Selecione sua ação de ataque:", "Arsenal", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, armas, armas[0]
                );
                if (escolhaArma == 0) {
                    armaEscolhida = 2;
                } else {
                    armaEscolhida = 3;
                }
                        
            } else if (bastao != null) {
                JOptionPane.showMessageDialog(null, "⚡ Você tem um bastão! Use ele para atacar!");
                armaEscolhida = 1;
            } else {
                JOptionPane.showMessageDialog(null, "👊 Você não tem armas! Vai ter que lutar no soco!");
                armaEscolhida = 3;
            }
            
            int dano = 0;
            if (armaEscolhida == 1) {
                JanelaJogo.log("⚡ Você usou o Bastão Elétrico!");
                dano = bastao.atacar(dinossauro);
            } else if (armaEscolhida == 2) {
                JanelaJogo.log("🔫 Você disparou a Arma de Dardos!");
                dano = arma.atacar(dinossauro);
                JanelaJogo.getInstancia().atualizarInterface();
            } else if (armaEscolhida == 3) {
                JanelaJogo.log("👊 Você atacou no soco!");
                dano = personagem.atacar(dinossauro);
            }
            
            dinossauro.levarDano(dano);
            JanelaJogo.log("💥 Você causou " + dano + " de dano no Dinossauro.");
            
            if (dinossauro.estaVivo()) {
                int danoDino = dinossauro.atacar(personagem);                     
                personagem.levarDano(danoDino);
                JanelaJogo.getInstancia().atualizarInterface();
                
                if (!(personagem.estaVivo())) {
                    JOptionPane.showMessageDialog(null, "💀 Você foi estraçalhado pelo Dinossauro...");
                    combate = false;
                }
            }
            
            if (!dinossauro.estaVivo()) {
                JOptionPane.showMessageDialog(null, "🎉 Você derrotou o dinossauro com sucesso!");
                JanelaJogo.log("🎉 Você derrotou o dinossauro!");
                tabuleiro.getMatriz()[dinossauro.getLinha()][dinossauro.getColuna()] = null;
                tabuleiro.removeDinossauro();
                combate = false; 
            }               
        }
        
        JanelaJogo.setJogoPausado(false);
    }

    public void iniciadoPorDinossauro (Tabuleiro tabuleiro) {
        
        JanelaJogo.setJogoPausado(true);
        
        JanelaJogo.log("\n🚨! Um dinossauro atacou voce de surpresa!");
        
        int dano = dinossauro.atacar(personagem);                     
        personagem.levarDano(dano);
        
        if (personagem.estaVivo()) {
            this.iniciadoPorJogador(tabuleiro);
        } else {
            JanelaJogo.log("💀 Você foi derrotado no ataque surpresa inicial... Fim de jogo!");
        }
    
    }
}