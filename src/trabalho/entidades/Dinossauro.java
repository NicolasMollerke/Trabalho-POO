/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import trabalho.modelo.ElementoDinamico;
import trabalho.Ataque;
import trabalho.GerenciadorMovimento;

/**
 *
 * @author nicol
 */
public abstract class Dinossauro extends ElementoDinamico implements Ataque{
    private int saude;
    private String nome;
    protected GerenciadorMovimento gerenciador;
    protected volatile boolean rodando = true;
    
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
    
    public String getNome () {
        return this.nome;
    }
    
    public void setGerenciador(GerenciadorMovimento gerenciador) {
        this.gerenciador = gerenciador;
    }
    
    public void pararThread() {
    this.rodando = false;
}

}
