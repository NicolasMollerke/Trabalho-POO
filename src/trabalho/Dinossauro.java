/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public abstract class Dinossauro extends ElementoDinamico{
    private int saude;
    
    public Dinossauro(int saude, int i, int j){
        super(i, j);
        this.saude = saude;
    }
    
    public void levarDano (int dano) {
        saude = saude - dano;
    }

    public boolean estaVivo(){
        return this.saude > 0;
    }

    public int getSaude() {
        return saude;
    }
    
    public int atacar(Personagem personagem) {
        return 1;
    }

}
