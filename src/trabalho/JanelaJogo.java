package trabalho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JanelaJogo extends JFrame {
    private Tabuleiro tabuleiro;
    private GerenciadorMovimento gerenciador;
    private PainelTabuleiro painelTabuleiro;
    
    private JTextArea areaTexto;
    private JLabel labelSaude;
    private JLabel labelPercepcao;
    private JLabel labelArma;

    public JanelaJogo(Tabuleiro tabuleiro, GerenciadorMovimento gerenciador) {
        this.tabuleiro = tabuleiro;
        this.gerenciador = gerenciador;
        
        setTitle("Jurassic Survival Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // 🌟 Layout Principal: Divide a janela em áreas (Centro, Leste, Oeste...)
        setLayout(new BorderLayout());

        this.painelTabuleiro = new PainelTabuleiro();
        add(painelTabuleiro, BorderLayout.CENTER);

        JPanel painelControle = criarPainelControle();
        add(painelControle, BorderLayout.EAST);

        pack(); 
        setLocationRelativeTo(null); // Centraliza na tela
        
        // Mantém o foco no teclado físico também
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                processarTeclado(e.getKeyCode());
            }
        });
        
        logarMensagem("Jogo Iniciado! Use as setas, WASD ou o PAD de botões para mover.");
    }

    // Cria a barra lateral direita
    private JPanel criarPainelControle() {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(300, tabuleiro.getTamanho() * 35));
        painel.setLayout(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(Color.LIGHT_GRAY);

        // 🌟 SOLUÇÃO: Painel agregador para o topo (guarda Status + Inventário)
        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(Color.LIGHT_GRAY);

        // --- SUBPAINEL DE STATUS ---
        JPanel painelStatus = new JPanel(new GridLayout(2, 1));
        painelStatus.setBackground(Color.LIGHT_GRAY);
        labelSaude = new JLabel("❤️ Saúde: " + tabuleiro.getPersonagem().getSaude());
        labelSaude.setFont(new Font("Arial", Font.BOLD, 16));
        labelPercepcao = new JLabel("👁️ Percepção: " + tabuleiro.getPersonagem().getPercecao());
        labelPercepcao.setFont(new Font("Arial", Font.BOLD, 16));
        painelStatus.add(labelSaude);
        painelStatus.add(labelPercepcao);
        
        // --- SUBPAINEL DE INVENTÁRIO ---
        JPanel painelInventario = new JPanel(new GridLayout(3, 1)); // Pronto para comportar Arma, Bastão e Kits futuramente
        painelInventario.setBackground(Color.LIGHT_GRAY);
        painelInventario.setBorder(BorderFactory.createTitledBorder("🎒 Inventário")); // Dá um visual de borda bem legal
        
        Inventario inv = tabuleiro.getPersonagem().getInventario();
        labelArma = new JLabel("");
        labelArma.setFont(new Font("Arial", Font.PLAIN, 14));

        if (inv.getArma() != null) {
            labelArma.setText("🔫 Arma de dardos (" + inv.getArma().getMunicao() + ")");
        }

        painelInventario.add(labelArma);
        
        // Empilha os dois blocos de texto no agregador
        painelTopo.add(painelStatus);
        painelTopo.add(Box.createRigidArea(new Dimension(0, 10))); // Dá um pequeno espaçamento entre eles
        painelTopo.add(painelInventario);

        // Envia o agregador completo de uma vez só para o Norte
        painel.add(painelTopo, BorderLayout.NORTH);
        
        // --- SUBPAINEL CENTRAL: Área de Texto (Logs do Jogo) ---
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setBackground(Color.WHITE);
        JScrollPane scrollTexto = new JScrollPane(areaTexto);
        painel.add(scrollTexto, BorderLayout.CENTER);

        // --- SUBPAINEL INFERIOR: PAD de Botoes de Movimentação ---
        /*JPanel painelBotoes = new JPanel(new GridLayout(3, 3, 5, 5));
        painelPad.setPreferredSize(new Dimension(280, 150));
        painelPad.setBackground(Color.LIGHT_GRAY);*/

       

        return painel;
    }

    // Processa os movimentos tanto do teclado físico quanto do PAD de botões
    private void processarTeclado(int keyCode) {
        Personagem jogador = tabuleiro.getPersonagem();
        int[] destino = new int[]{jogador.getLinha(), jogador.getColuna()};
        boolean debug = tabuleiro.getDebug();

        switch (keyCode) {
            case KeyEvent.VK_UP: destino[0]--; break;
            case KeyEvent.VK_DOWN: destino[0]++; break;
            case KeyEvent.VK_LEFT: destino[1]--; break;
            case KeyEvent.VK_RIGHT: destino[1]++; break;
            case KeyEvent.VK_D: 
                if (debug) {
                    tabuleiro.desativarDebug();
                } else {
                    tabuleiro.ativarDebug();
                }
                atualizarInterface();
                return;
            
            default: return; 
        }

        if (gerenciador.posicaoValida(destino[0], destino[1], jogador)) {
            gerenciador.atualizarPosicao(jogador, destino[0], destino[1]);
            gerenciador.moverDinossauros();
            atualizarInterface();
            
            if (!jogador.estaVivo()) {
                logarMensagem("❌ Fim de Jogo! Você morreu.");
                JOptionPane.showMessageDialog(this, "Seu personagem morreu! Você perdeu.");
            }
            if (tabuleiro.semDinossauros()) {
                logarMensagem("🏆 Vitória! Todos os dinossauros foram eliminados.");
                JOptionPane.showMessageDialog(this, "Parabéns! Você venceu!");
            }
        } else {
            logarMensagem("⚠ Movimento inválido ou limite do mapa!");
        }
        
        // Devolve o foco para a janela registrar o teclado físico
        this.requestFocusInWindow();
    }

    // Auxiliar para atualizar textos e redesenhar a tela
    private void atualizarInterface() {
        labelSaude.setText("❤️ Saúde: " + tabuleiro.getPersonagem().getSaude());
        labelPercepcao.setText("👁️ Percepção: " + tabuleiro.getPersonagem().getPercecao());
        painelTabuleiro.repaint();
    }

    // Escreve novas mensagens na janelinha de texto lateral
    public void logarMensagem(String msg) {
        areaTexto.append(msg + "\n");
        areaTexto.setCaretPosition(areaTexto.getDocument().getLength()); // Rola o scroll para baixo
    }

    // O JPanel do tabuleiro permanece interno aqui
    private class PainelTabuleiro extends JPanel {
        private final int TAMANHO_CELULA = 35;

        public PainelTabuleiro() {
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
                        g.setColor(new Color(40, 40, 40));
                        g.fillRect(xPixel, yPixel, TAMANHO_CELULA, TAMANHO_CELULA);
                    } else {
                        Elemento atual = matriz[i][j];
                        if (atual == null) {
                            g.setColor(new Color(34, 139, 34));
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
    }
}