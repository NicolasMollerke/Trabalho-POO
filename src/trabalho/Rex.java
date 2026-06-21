/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Rex extends Dinossauro{
    public Rex () {
        super(3);
    }

    @Override
    public int getDanoAtaque(){
        return 2;
    }

    @Override
    public boolean podeSerAtingidoComMaoNua(){
        return false;
    }

    @Override
    public void mover(Tabuleiro tabuleiro, Personagem personagem){

    }
}
