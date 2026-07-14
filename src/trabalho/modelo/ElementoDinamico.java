/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.modelo;

/**
 *
 * @author nicol
 */
public abstract class ElementoDinamico extends Elemento {
    public ElementoDinamico(int linha, int coluna) {
        super(linha, coluna);
    }

    public void setPosicao (int x, int y) {
        this.linha = x;
        this.coluna = y;
    }
    
    public int getLinha() {
        return linha;
    }
    
    public int getColuna() {
        return coluna;
    }
}
