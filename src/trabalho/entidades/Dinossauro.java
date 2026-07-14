/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import trabalho.modelo.ElementoDinamico;

/**
 *
 * @author nicol
 */
public abstract class Dinossauro extends ElementoDinamico{
    private int saude;
    private String nome;
    
    public Dinossauro(int saude, int i, int j, String nome){
        super(i, j);
        this.saude = saude;
        this.nome = nome;
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
    
    public String getNome () {
        return this.nome;
    }

}
