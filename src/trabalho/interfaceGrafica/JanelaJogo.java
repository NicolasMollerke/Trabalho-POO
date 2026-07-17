package trabalho.interfaceGrafica;

import trabalho.itens.Inventario;
import trabalho.modelo.Tabuleiro;
import trabalho.entidades.Personagem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import trabalho.mecanicas.GerenciadorMovimento;
import trabalho.entidades.Dinossauro;

public class JanelaJogo extends JFrame {
    private Tabuleiro tabuleiro;
    private GerenciadorMovimento gerenciador;
    private PainelTabuleiro painelTabuleiro;
    private static JanelaJogo instancia;
    private static volatile boolean jogoPausado = false;
    
    private JTextArea areaTexto;
    private JLabel labelSaude;
    private JLabel labelPercepcao;
    private JLabel labelArma;
    private JLabel labelBastao;
    private JLabel labelKit;

    public JanelaJogo(Tabuleiro tabuleiro, GerenciadorMovimento gerenciador) {
        this.tabuleiro = tabuleiro;
        this.gerenciador = gerenciador;
        instancia = this;
        
        setTitle("Sobrevivência Jurássica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        setLayout(new BorderLayout());

        this.painelTabuleiro = new PainelTabuleiro(this.tabuleiro);
        add(painelTabuleiro, BorderLayout.CENTER);

        JPanel painelControle = criarPainelControle();
        add(painelControle, BorderLayout.EAST);

        pack(); 
        setLocationRelativeTo(null); // Centraliza na tela
        
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                processarTeclado(e.getKeyCode());
            }
        });
        
        logarMensagem("Jogo Iniciado! Use as setas para mover.");
        
        SwingUtilities.invokeLater(() -> {
            atualizarInterface();
        });
    }

    //barra lateral direita
    private JPanel criarPainelControle() {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(300, tabuleiro.getTamanho() * 35));
        painel.setLayout(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(Color.LIGHT_GRAY);

        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(Color.LIGHT_GRAY);

        JPanel painelStatus = new JPanel(new GridLayout(2, 1));
        painelStatus.setBackground(Color.LIGHT_GRAY);
        labelSaude = new JLabel("❤️ Saúde: " + tabuleiro.getPersonagem().getSaude());
        labelSaude.setFont(new Font("Arial", Font.BOLD, 16));
        labelPercepcao = new JLabel("👁️ Percepção: " + tabuleiro.getPersonagem().getPercecao());
        labelPercepcao.setFont(new Font("Arial", Font.BOLD, 16));
        painelStatus.add(labelSaude);
        painelStatus.add(labelPercepcao);
        
        JPanel painelInventario = new JPanel(new GridLayout(3, 1)); 
        painelInventario.setBackground(Color.LIGHT_GRAY);
        painelInventario.setBorder(BorderFactory.createTitledBorder("🎒 Inventário"));
        
        labelArma = new JLabel("");
        labelArma.setFont(new Font("Arial", Font.PLAIN, 14));
        labelBastao = new JLabel("");
        labelBastao.setFont(new Font("Arial", Font.PLAIN, 14));
        labelKit = new JLabel("");
        labelKit.setFont(new Font("Arial", Font.PLAIN, 14));

        painelInventario.add(labelArma);
        painelInventario.add(labelBastao);
        painelInventario.add(labelKit);
        
        painelTopo.add(painelStatus);
        painelTopo.add(Box.createRigidArea(new Dimension(0, 10))); 
        painelTopo.add(painelInventario);

        painel.add(painelTopo, BorderLayout.NORTH);
        
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setBackground(Color.WHITE);
        JScrollPane scrollTexto = new JScrollPane(areaTexto);
        painel.add(scrollTexto, BorderLayout.CENTER);

        JPanel painelOpcoes = new JPanel(new GridLayout(2, 2, 10, 10));
        painelOpcoes.setBackground(Color.LIGHT_GRAY);

        JButton btn1 = new JButton("Reiniciar Jogo");
        JButton btn2 = new JButton("Novo Jogo");
        JButton btn3 = new JButton("Sair do Jogo");

        btn1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btn1.setFocusPainted(false); 
        btn1.setBackground(Color.DARK_GRAY);
        btn1.setForeground(Color.WHITE);
        btn1.setFont(new Font("Arial", Font.BOLD, 14));


        btn2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btn2.setFocusPainted(false);
        btn2.setBackground(Color.DARK_GRAY);
        btn2.setForeground(Color.WHITE);
        btn2.setFont(new Font("Arial", Font.BOLD, 14));
        
        btn3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btn3.setFocusPainted(false);
        btn3.setBackground(Color.DARK_GRAY);
        btn3.setForeground(Color.WHITE);
        btn3.setFont(new Font("Arial", Font.BOLD, 14));

        btn1.addActionListener(e -> {
           
            setJogoPausado(false);
            
            this.pararThreadsDinos();
            
            String arquivo = tabuleiro.getNomeTabuleiro();
            Personagem jogador = new Personagem(tabuleiro.getPersonagem().getPercecao());
                        
            this.tabuleiro = new Tabuleiro(arquivo, jogador);
            this.gerenciador = new GerenciadorMovimento(this.tabuleiro);
            
            this.tabuleiro.iniciarThreadsDinossauros(this.gerenciador);
            
            this.painelTabuleiro.setTabuleiro(this.tabuleiro);

            areaTexto.setText("");
            logarMensagem("O jogo foi reiniciado no MESMO mapa!");

            atualizarInterface();
            
            this.requestFocusInWindow();
        });
        
        btn2.addActionListener(e -> {
            JanelaJogo.finalizarJogoAtual();
            
            Trabalho.iniciarNovoJogo();
        });
        
        btn3.addActionListener(e -> {
            tabuleiro.salvarMapa("src/trabalho/arquivos/save_mapa.txt");
            tabuleiro.getPersonagem().salvarPersonagem("src/trabalho/arquivos/save_jogador.txt");
                   
            System.exit(0);
        });

        painelOpcoes.add(btn1);
        painelOpcoes.add(btn2);
        painelOpcoes.add(btn3);


        painel.add(painelOpcoes, BorderLayout.SOUTH);

       

        return painel;
    }

    private void processarTeclado(int keyCode) {
        
        if (isJogoPausado()) {
            return;
        }
        
        Personagem jogador = tabuleiro.getPersonagem();
        int[] coordenadas = new int[]{ jogador.getLinha(), jogador.getColuna() };        
        boolean debug = tabuleiro.getDebug();

        switch (keyCode) {
            case KeyEvent.VK_UP: coordenadas[0]--; break;
            case KeyEvent.VK_DOWN: coordenadas[0]++; break;
            case KeyEvent.VK_LEFT: coordenadas[1]--; break;
            case KeyEvent.VK_RIGHT: coordenadas[1]++; break;
            case KeyEvent.VK_D: 
                if (debug) {
                    tabuleiro.desativarDebug();
                } else {
                    tabuleiro.ativarDebug();
                }
                atualizarInterface();
                return;
                
            case KeyEvent.VK_H: jogador.usarKit(); return;
            
            default: return; 
        }

        if (gerenciador.posicaoValida(coordenadas[0], coordenadas[1], jogador)) {
            jogador.moverJogador(coordenadas[0], coordenadas[1], gerenciador);
            atualizarInterface();
            
            if (!jogador.estaVivo()) {
                setJogoPausado(true);
                logarMensagem("❌ Fim de Jogo! Você morreu.");
                JOptionPane.showMessageDialog(this, "Seu personagem morreu! Você perdeu.");
                JanelaJogo.finalizarJogoAtual();
                Trabalho.iniciarNovoJogo();
            }
            if (tabuleiro.semDinossauros()) {
                setJogoPausado(true);
                logarMensagem("🏆 Vitória! Todos os dinossauros foram eliminados.");
                JOptionPane.showMessageDialog(this, "Parabéns! Você venceu!");
            }
        } else {
            logarMensagem("⚠ Movimento inválido ou limite do mapa!");
        }
        
        this.requestFocusInWindow();
    }

    public void atualizarInterface() {
        SwingUtilities.invokeLater(() -> { //invokeLater cria uma fila de ações
            labelSaude.setText("❤️ Saúde: " + tabuleiro.getPersonagem().getSaude());
            labelPercepcao.setText("👁️ Percepção: " + tabuleiro.getPersonagem().getPercecao());

            Inventario inv = tabuleiro.getPersonagem().getInventario();


            if (inv.getArma() != null) {
                labelArma.setText("🔫 Arma de Dardos (" + inv.getArma().getMunicao() + ")");
            } else {
                labelArma.setText("");
            }

            if (inv.getBastao() != null) {
                labelBastao.setText("⚡ Bastão de Choque");
            } else {
                labelBastao.setText("");
            }

            if (inv.getKit() != null) {
                labelKit.setText("➕ Kit Médico");
            } else {
                labelKit.setText("");
            }

            painelTabuleiro.repaint();
        });
    }

    public void logarMensagem(String msg) {
        SwingUtilities.invokeLater(() -> {
            areaTexto.append(msg + "\n");
            areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
        });
    }
    
    //static para ser chamado globalmete
    public static void log(String mensagem) {
        if (instancia != null && instancia.areaTexto != null) {
            instancia.logarMensagem(mensagem);
        } else {
            System.out.println(mensagem); 
        }
    }
    
    public static JanelaJogo getInstancia() {
        return instancia;
    }
    
    public static boolean isJogoPausado() {
        return jogoPausado;
    }

    public static void setJogoPausado(boolean status) {
        jogoPausado = status;
    }
    
    //novo jogo
    public static void finalizarJogoAtual() {
        if (instancia != null) {
            //despausa o jogo
            setJogoPausado(false);

            //desliga as threads
            Tabuleiro tab = instancia.tabuleiro;
            if (tab != null) {
                for (int i = 0; i < tab.getTamanho(); i++) {
                    for (int j = 0; j < tab.getTamanho(); j++) {
                        if (tab.getMatriz()[i][j] instanceof Dinossauro) {
                            ((Dinossauro) tab.getMatriz()[i][j]).pararThread();
                        }
                    }
                }
            }

            //fecha a janela
            instancia.dispose();
            instancia = null;
        }
    }
    
    //para as threads
    public void pararThreadsDinos() {
        Tabuleiro tab = this.tabuleiro;
        if (tab != null) {
            for (int i = 0; i < tab.getTamanho(); i++) {
                for (int j = 0; j < tab.getTamanho(); j++) {
                    if (tab.getMatriz()[i][j] instanceof Dinossauro) {
                        ((Dinossauro) tab.getMatriz()[i][j]).pararThread();
                    }
                }
            }
        }
    }


}