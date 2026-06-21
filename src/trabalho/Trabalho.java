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
        System.out.println("1.Fácil");
        System.out.println("2.Médio");
        System.out.println("3.Difícil");
        
        int dificuldade = teclado.nextInt();
        
        Personagem personagem = null; 
        
        switch (dificuldade) {
            case 1:
                // >>> 2. APENAS INSTANCIA (Sem repetir a palavra "Personagem") <<<
                personagem = new Personagem(3); 
                break; // IMPORTANTE: Para o switch aqui e vai para o final
                
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
        
        
    }
}
