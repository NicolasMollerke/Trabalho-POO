/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho;
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
                System.out.println("Opção inválida! Criando personagem com dificuldade padrão (2 vidas).");
                personagem = new Personagem(2);
                break;
        }
        
        Tabuleiro tabuleiro = new Tabuleiro("tabuleiro.txt", personagem);
        
        int opcao;
        
        do {
            tabuleiro.mostrarTabuleiro();
            System.out.println("1.Mover");
            System.out.println("2.Curar");
            System.out.println("3.Debug");
            System.out.println("4.Mover");
            
            opcao = teclado.nextInt();
            
            switch (opcao) {
                case 1: 
                    personagem.moverJogador(tabuleiro);
                    tabuleiro.moverDinossauros();
                    break;
                case 3:
                    tabuleiro.ativarDebug();
                    break;
            }
            
        } while (opcao != 4);
    }
}
