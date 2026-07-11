/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Inventario {
    private Arma arma;
    private Bastao bastao;
    private Kit kit;
    
    public Inventario() {
        this.arma = null;
        this.bastao = null;
        this.kit = null;
    }
    
    public Arma getArma() {
        return this.arma;
    }
    
    public Bastao getBastao() {
        return this.bastao;
    }
    
    public Kit getKit() {
        return this.kit;
    }
    
    public void adquireArma(Arma arma) {
        this.arma = arma;
    }
    
    public void adquireBastao(Bastao bastao) {
        this.bastao = bastao;
    }
    
    public void adquireKit(Kit kit) {
        this.kit = kit;
    }
    
    public void gastaKit() {
        this.kit = null;
    }
    
    public void restauraInventario() {
        this.arma = null;
        this.bastao = null;
        this.kit = null;
    }
}
