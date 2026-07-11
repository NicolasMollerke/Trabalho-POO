/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class Trodonte extends Dinossauro implements Movel{
    private Personagem personagem;
    
    public Trodonte (int i, int j, Personagem personagem) {
        super(2, i, j);
        this.personagem = personagem;
    }

    public int[] mover(){
        int diferencaLinha = personagem.linha - this.linha;
        int diferencaColuna = personagem.coluna - this.coluna;
        int linhaNova = this.linha;
        int colunaNova = this.coluna;
        int[] coordenadas = new int[2]; 

        
        int tentativa = 1;
        
        while (tentativa <= 2) {
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
            
            tentativa++;
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
