package trabalho;

import trabalho.modelo.Tabuleiro;
import trabalho.entidades.Personagem;
import java.util.Random;
import javax.swing.JOptionPane;

public class Trabalho {

    public static void main(String[] args) {        
        iniciarNovoJogo();
    }

    public static void iniciarNovoJogo() {
        String[] base = {"Novo Jogo", "Continuar Jogo"};
        int escolhaBase = JOptionPane.showOptionDialog(
                null, 
                "O que voce quer fazer:", 
                "Título da Janela",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                base, 
                base[1]
        );
        
        if (escolhaBase == 1){
            
            Personagem jogador = new Personagem(0);

            jogador.carregarJogador("src/trabalho/arquivos/save_jogador.txt");

            Tabuleiro tabuleiro = new Tabuleiro("src/trabalho/arquivos/save_mapa.txt", jogador);

            GerenciadorMovimento gerenciador = new GerenciadorMovimento(tabuleiro);
            
            java.awt.EventQueue.invokeLater(() -> {
                JanelaJogo tela = new JanelaJogo(tabuleiro, gerenciador);
                tela.setVisible(true);
            });
            
            return;
        }
        
        String[] opcoes = {"Fácil (Percepção 3)", "Médio (Percepção 2)", "Difícil (Percepção 1)"};
        int escolha = JOptionPane.showOptionDialog(
                null, 
                "Escolha a dificuldade do jogo:", 
                "Seleção de Dificuldade", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opcoes, 
                opcoes[1]
        );

        if (escolha == JOptionPane.CLOSED_OPTION) {
            System.exit(0); 
        }

        int percepcao = 2;
        if (escolha == 0) percepcao = 3;
        if (escolha == 2) percepcao = 1;

        Personagem personagem = new Personagem(percepcao);
        
        Random gerador = new Random();
        int random = gerador.nextInt(3) + 1;
        
        Tabuleiro tabuleiro = new Tabuleiro("src/trabalho/arquivos/tabuleiro" + random + ".txt", personagem); 
        GerenciadorMovimento gerenciador = new GerenciadorMovimento(tabuleiro);
        
        java.awt.EventQueue.invokeLater(() -> {
            JanelaJogo tela = new JanelaJogo(tabuleiro, gerenciador);
            tela.setVisible(true);
        });
    }
}