/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class Trabalho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        Scanner teclado = new Scanner(System.in);
        boolean jogoRodando = true;
        
        while (jogoRodando) {
            System.out.println("===== MENU INICIAL =====");
            System.out.println("1. Jogar");
            System.out.println("2. Sair");
            
            int menuInicial = teclado.nextInt();
            
            if (menuInicial == 2) {
                System.out.println("Saindo do jogo... Ate mais!");
                jogoRodando = false;
                break;
            } else if (menuInicial != 1) {
                System.out.println("Opção invalida!");
                continue;
            }
            
            boolean escolhaDificuldade = true;
            
            while (escolhaDificuldade) {
                System.out.println("Ecolha sua dificuldade:");
                System.out.println("1.Facil");
                System.out.println("2.Medio");
                System.out.println("3.Dificil");
                
                int dificuldade = teclado.nextInt();
                
                Personagem personagem = null; 
                
                switch (dificuldade) {
                    case 1:
                        personagem = new Personagem(3); 
                        break;
                        
                    case 2:
                        personagem = new Personagem(2);
                        break;
                        
                    case 3:
                        personagem = new Personagem(1);
                        break;
                        
                    default:
                        System.out.println("Opção invalida! Criando personagem com dificuldade padrao (2 vidas).");
                        personagem = new Personagem(2);
                        break;
                }
                
                boolean mesmaPartida = true;
                
                Random gerador = new Random();
                    
                int random = gerador.nextInt(3) + 1;
                
                
                while (mesmaPartida) {      
                    Tabuleiro tabuleiro;      
                    
                    tabuleiro = new Tabuleiro ("tabuleiro" + random + ".txt", personagem);
                    
                    int opcao;
                    
                    do {
                        tabuleiro.mostrarTabuleiro();
                        System.out.println("1.Mover");
                        System.out.println("2.Curar");
                        System.out.println("3.Debug");
                        System.out.println("4.Sair");
                        
                        opcao = teclado.nextInt();
                        
                        switch (opcao) {
                            case 1: 
                                personagem.mover(tabuleiro);
                                tabuleiro.moverDinossauros();
                                break;
                            case 2:
                                personagem.usarKit();
                                break;
                            case 3:
                                if (!tabuleiro.getDebug())
                                        tabuleiro.ativarDebug();
                                else {
                                    tabuleiro.desativarDebug();
                                }
                                break;
                        }
                        
                        if (!(personagem.estaVivo())) {
                            System.out.println("Seu personagem morreu! Voce perdeu o jogo");
                            opcao = 4;
                        } 
                        
                        if (tabuleiro.semDinossauros()) {
                            System.out.println("Todos dinossauros derrotados, voce venceu!");
                            opcao = 4;
                        }
                        
                    } while (opcao != 4);
                    
                    System.out.println("\nO que deseja fazer?");
                    System.out.println("1. Reiniciar jogo");
                    System.out.println("2. Novo jogo");
                    
                    int escolhaFim = teclado.nextInt();
                    
                    if (escolhaFim == 1) {
                        System.out.println("🔄 Reiniciando com as posicoes iniciais...");
                        personagem.restaurarPersonagem();
                        tabuleiro.desativarDebug();
                    } else if (escolhaFim == 2) {
                        System.out.println("🔄Voltando para a selecao de dificuldade...");
                        mesmaPartida = false;
                        escolhaDificuldade = false; 
                    } else {
                        System.out.println("Opçao invalida, voltando ao Menu Inicial por padrao.");
                        mesmaPartida = false;
                        escolhaDificuldade = false;
                    }
                }
            }
        }
    }
}