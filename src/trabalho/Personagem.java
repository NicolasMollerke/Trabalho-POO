/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class Personagem extends ElementoDinamico implements Movel{
    private int percepcao;
    private int saude;
    private Inventario inventario;
    
    public Personagem (int percepcao) {
        super(0,0);
        this.percepcao = percepcao;
        this.saude = 5;
        this.inventario = new Inventario();
    }
    
    public String getSimbolo() { 
        return "P"; 
    }
    
    public boolean estaVivo () {
        return saude > 0;
    }
   
    public Inventario getInventario () {
        return inventario;
    }
    
    public int getPercecao() {
        return percepcao;
    }
    
    public int getSaude() {
        return saude;
    }
    
    public void levarDano(int dano) {
        Random gerador = new Random();
        int dado = gerador.nextInt(3) + 1;
        
        if (dado <= percepcao) {
            JanelaJogo.log("Voce desviou do ataque!");
        } else {
            this.saude = this.saude - dano;
            JanelaJogo.log("🦖 O Dinossauro contra-atacou e te deu " + dano + " de dano!");
        }
    }
    
    public int[] mover() {
        int[] coordenadas = new int[2]; 
        
        Scanner teclado = new Scanner(System.in);

        System.out.println("Para onde voce quer se mover?");
        System.out.println("1.^ Cima");
        System.out.println("2.< Esquerda");
        System.out.println("3.> Direita");
        System.out.println("4.v Baixo"); 

        int direcao = teclado.nextInt();

        int linhaNova = this.linha;
        int colunaNova = this.coluna;

        switch (direcao) {
            case 1:
                linhaNova--; // Cima
                break;
            case 2:
                colunaNova--; // Esquerda
                break;
            case 3:
                colunaNova++; // Direita
                break;
            case 4:
                linhaNova++;  // Baixo
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
        coordenadas[0] = linhaNova;
        coordenadas[1] = colunaNova;
        
        return coordenadas;
    }
    
    public void usarKit () {
        if (inventario.getKit() != null) {
            int cura = inventario.getKit().curar(this);
            this.saude += cura;
        } else {
            JanelaJogo.log("\n[!] Voce nao possui nenhum Kit Medico no seu inventario!");
        }
    }
    
    public int atacar(Dinossauro alvo) {
        java.util.Random gerador = new java.util.Random();
        int dado = gerador.nextInt(6) + 1;
        int dano=0;
        
        if (!(alvo instanceof Rex)) {
            if (dado == 6) {
                JanelaJogo.log("Voce deu um golpe critico! O dinossauro recebeu 2 de dano!");
                dano = 2;
            } else if (dado == 1 || dado == 2) {
                JanelaJogo.log("Voce errou o ataque!");
                dano = 0;
            } else {
                if (alvo instanceof Trodonte) {
                    JanelaJogo.log("Trodonte nao levou dano!");
                } else {
                    dano = 1;
                }
            }
        } else {
            JanelaJogo.log("Rex nao leva dano sem armas!");
        }      
        
        return dano;
    }
    
    public void restaurarPersonagem() {
        this.saude = 5;
        inventario.restauraInventario();
        this.setPosicao(0, 0);
    }
}
    