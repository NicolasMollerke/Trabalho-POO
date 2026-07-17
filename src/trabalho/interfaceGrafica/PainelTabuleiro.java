/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.interfaceGrafica;

import trabalho.modelo.Elemento;
import trabalho.modelo.Tabuleiro;
import trabalho.entidades.Personagem;
import trabalho.itens.Parede; 
import trabalho.itens.Caixa;    
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import trabalho.itens.Caixa;
import trabalho.itens.Parede;
import trabalho.entidades.Compsognato;
import trabalho.entidades.Rex;
import trabalho.entidades.Trodonte;
import trabalho.entidades.Velociraptor;

/**
 *
 * @author Windows 11
 */
public class PainelTabuleiro extends JPanel{
        private final int TAMANHO_CELULA = 45;
        private Tabuleiro tabuleiro;
        private Image imagemTrodonte;
        private Image imagemCompsoganto;
        private Image imagemVelociraptor;
        private Image imagemRex;
        private Image imagemCaixa;
        private Image imagemParede;
        private Image imagemGrama;
        private Image imagemPersonagem;
        private Image imagemNevoa;
        

        public PainelTabuleiro(Tabuleiro tabuleiro) {
            this.tabuleiro = tabuleiro;  
            int dim = tabuleiro.getTamanho() * TAMANHO_CELULA;
            setPreferredSize(new Dimension(dim, dim));
            setBackground(Color.BLACK);
            this.imagemTrodonte = carregarImagem("/trabalho/imagens/Trodonte.png");
            this.imagemCompsoganto = carregarImagem("/trabalho/imagens/Compsognato.png");
            this.imagemVelociraptor = carregarImagem("/trabalho/imagens/Velociraptor.png");
            this.imagemRex = carregarImagem("/trabalho/imagens/Rex.png");
            this.imagemCaixa = carregarImagem("/trabalho/imagens/Caixa.jpg");
            this.imagemParede = carregarImagem("/trabalho/imagens/Parede.jpg");
            this.imagemGrama = carregarImagem("/trabalho/imagens/Grama.jpg");
            this.imagemPersonagem = carregarImagem("/trabalho/imagens/Personagem.png");
            this.imagemNevoa = carregarImagem("/trabalho/imagens/Nevoa.png");
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            Elemento[][] matriz = tabuleiro.getMatriz();
            int tamanho = tabuleiro.getTamanho();
            Personagem p = tabuleiro.getPersonagem();
            boolean[][] visivel = tabuleiro.campoDeVisao(p.getLinha(), p.getColuna());

            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    int xPixel = j * TAMANHO_CELULA;
                    int yPixel = i * TAMANHO_CELULA;

                    if (!visivel[i][j] && !tabuleiro.getDebug()) {
                        g.drawImage(imagemNevoa, xPixel, yPixel, null);
                    } else {
                        Elemento atual = matriz[i][j];
                        g.drawImage(imagemGrama, xPixel, yPixel, null);

                        if (atual instanceof Personagem) {
                            g.drawImage(imagemPersonagem, xPixel, yPixel, null);
                        } else if (atual instanceof Parede) {
                            g.drawImage(imagemParede, xPixel, yPixel, null);
                        } else if (atual instanceof Caixa) {
                            g.drawImage(imagemCaixa, xPixel, yPixel, null);
                        } else if (atual instanceof Trodonte) {
                            g.drawImage(imagemTrodonte, xPixel, yPixel, null);
                        } else if (atual instanceof Compsognato) {
                            g.setColor(Color.WHITE);
                            g.drawImage(imagemCompsoganto, xPixel, yPixel, null);
                        } else if (atual instanceof Velociraptor) {
                            g.drawImage(imagemVelociraptor, xPixel, yPixel, null);
                        } else if (atual instanceof Rex) {
                            g.drawImage(imagemRex, xPixel, yPixel, null);
                        }
                    } 
                    g.setColor(new Color(0, 0, 0, 50));
                    g.drawRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                }
            }
        }
        
        private Image carregarImagem(String caminho) {
            java.net.URL imgURL = getClass().getResource(caminho);
            if (imgURL != null) {
                ImageIcon iconeOriginal = new ImageIcon(imgURL);

                Image imgEscalada = iconeOriginal.getImage().getScaledInstance(TAMANHO_CELULA, TAMANHO_CELULA, Image.SCALE_SMOOTH);

                ImageIcon iconePronto = new ImageIcon(imgEscalada);

                return iconePronto.getImage();
            } else {
                System.err.println("Erro: Não foi possível encontrar a imagem no caminho: " + caminho);
                return null;
            }
        }
        
        public void setTabuleiro(Tabuleiro tabuleiro) {
            this.tabuleiro = tabuleiro;
        }
}
