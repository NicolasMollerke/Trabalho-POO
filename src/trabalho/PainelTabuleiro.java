/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Windows 11
 */
public class PainelTabuleiro extends JPanel{
        private final int TAMANHO_CELULA = 35;
        private Tabuleiro tabuleiro;

        public PainelTabuleiro(Tabuleiro tabuleiro) {
            this.tabuleiro = tabuleiro;  
            int dim = tabuleiro.getTamanho() * TAMANHO_CELULA;
            setPreferredSize(new Dimension(dim, dim));
            setBackground(Color.BLACK);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Elemento[][] matriz = tabuleiro.getMatriz();
            int tamanho = tabuleiro.getTamanho();
            Personagem p = tabuleiro.getPersonagem();
            boolean[][] visivel = tabuleiro.campoDeVisao(p.getLinha(), p.getColuna());

            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    int xPixel = j * TAMANHO_CELULA;
                    int yPixel = i * TAMANHO_CELULA;

                    if (!visivel[i][j] && !tabuleiro.getDebug()) {
                        g.setColor(Color.BLACK);
                        g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                    } else {
                        Elemento atual = matriz[i][j];
                        if (atual == null) {
                            g.setColor(Color.WHITE);
                            g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                        } else if (atual instanceof Personagem) {
                            g.setColor(Color.BLUE);
                            g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                        } else if (atual instanceof Parede) {
                            g.setColor(Color.DARK_GRAY);
                            g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                        } else if (atual instanceof Caixa) {
                            g.setColor(Color.ORANGE);
                            g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                        } else if (atual instanceof Dinossauro) {
                            g.setColor(Color.RED);
                            g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                        }
                    }
                    g.setColor(new Color(0, 0, 0, 50));
                    g.drawRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                }
            }
        }
        
        public void setTabuleiro(Tabuleiro tabuleiro) {
            this.tabuleiro = tabuleiro;
        }
}
