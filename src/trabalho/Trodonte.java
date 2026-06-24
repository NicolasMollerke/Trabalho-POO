/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Trodonte extends Dinossauro{
    public Trodonte (int i, int j) {
        super(2, i, j);
    }

    public void mover(Tabuleiro tabuleiro){
        Personagem personagem = tabuleiro.getPersonagem();
        Elemento[][] matriz = tabuleiro.getMatriz();

        
        int diferencaLinha = personagem.linha - this.linha;
        int diferencaColuna = personagem.coluna - this.coluna;
        
        boolean mover = false;
        int tentativa = 1;
        
        while (!mover && tentativa <= 2) {
            int linhaNova = this.linha;
            int colunaNova = this.coluna;
            
            if (tentativa == 1) {
                if (Math.abs(diferencaColuna) > Math.abs(diferencaLinha)){
                    if (diferencaColuna > 0) colunaNova++;
                    else if (diferencaColuna < 0) colunaNova--;
                } else {
                    if (diferencaLinha > 0) linhaNova++;
                    else if (diferencaLinha < 0) linhaNova--;
                }
            } else if (tentativa == 2) {
                if (Math.abs(diferencaColuna) > Math.abs(diferencaLinha)){
                    if (diferencaLinha > 0) linhaNova++;
                    else if (diferencaLinha < 0) linhaNova--;
                } else {
                    if (diferencaColuna > 0) colunaNova++;
                    else if (diferencaColuna < 0) colunaNova--;
                }
            }
            
            if (linhaNova >= 0 && linhaNova < tabuleiro.getTamanho() && colunaNova >=0 && colunaNova < tabuleiro.getTamanho()){
                if (matriz[linhaNova][colunaNova] == null) {
                    matriz[this.linha][this.coluna] = null;
                    matriz[linhaNova][colunaNova] = this;
                        
                    this.linha = linhaNova;
                    this.coluna = colunaNova;
                        
                        mover = true;
                }            
            }
            
            tentativa++;
        }
    }
    
    public String getSimbolo() { 
        return "T"; 
    }
}
