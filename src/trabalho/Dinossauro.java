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

    
    public Dinossauro(int saude, int i, int j){
        super(i, j);
        this.saude = saude;
    }

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
