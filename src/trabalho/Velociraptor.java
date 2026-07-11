/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.util.Random;

/**
 *
 * @author nicol
 */
public class Velociraptor extends Dinossauro implements Movel{
    public Velociraptor (int i, int j) {
        super(2, i, j);
    }

    public int[] mover(){
        Random gerador = new Random();
       
        int direcao = gerador.nextInt(4) + 1;
                
        int linhaNova = this.linha;
        int colunaNova = this.coluna;
        int[] coordenadas = new int[2]; 
                
        if (direcao == 1) linhaNova--; //Cima
        else if (direcao == 2) colunaNova--; // Esquerda
        else if (direcao == 3) colunaNova++; // Direita
        else if (direcao == 4) linhaNova++;  // Baixo
        
        coordenadas[0] = linhaNova;
        coordenadas[1] = colunaNova;
        
        return coordenadas;
    }
    
    public int atacar(Personagem personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "V"; 
    }
}
