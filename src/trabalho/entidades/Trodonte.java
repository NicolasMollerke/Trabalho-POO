/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import trabalho.Movel;

/**
 *
 * @author nicol
 */
public class Trodonte extends Dinossauro implements Movel{
    private Personagem personagem;
    
    public Trodonte (int i, int j, Personagem personagem) {
        super(2, i, j, "Trodonte");
        this.personagem = personagem;
    }

public int[] mover() {
        int diferencaLinha = personagem.getLinha() - this.linha;
        int diferencaColuna = personagem.getColuna() - this.coluna;
        
        int coordenadas[] = new int[2];
        
        int linhaNova = this.linha;
        int colunaNova = this.coluna;

        if (Math.abs(diferencaColuna) > Math.abs(diferencaLinha)) {
            if (diferencaColuna > 0) {
                colunaNova++;
            } else if (diferencaColuna < 0) {
                colunaNova--;
            }
        } 
        else {
            if (diferencaLinha > 0) {
                linhaNova++;
            } else if (diferencaLinha < 0) {
                linhaNova--;
            }
        }
        
        coordenadas[0] = linhaNova;
        coordenadas[1] = colunaNova;
                
        return coordenadas;
    }
    
    public int atacar(Personagem personagem) {
        return 1;
    }
    
    public String getSimbolo() { 
        return "T"; 
    }
}
