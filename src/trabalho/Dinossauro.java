/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public abstract class Dinossauro extends Elemento{
    private int saude;

    
    public Dinossauro(int saude){
        this.saude = saude;
    }

    public abstract void mover(Tabuleiro tabuleiro, Personagem personagem);

    public void receber(int dano) {
        this.saude -= dano;
    }

    public boolean estaVivo(){
        return this.saude > 0;
    }

    public int getDanoAtaque(){
        return 1;
    }

    public boolean podeSerAtingidoPorDardo(){
        return true;
    }

    public boolean podeSerAtingidoPorMaoNua(){
        return true;
    }

}
