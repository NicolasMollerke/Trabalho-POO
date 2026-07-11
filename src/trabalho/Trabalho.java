package trabalho;

import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JOptionPane;

public class Trabalho {

    public static void main(String[] args) {        
        // 1. MENU GRÁFICO: Escolha de Dificuldade usando botões visuais
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

        int percepcao = 2; // Padrão Médio
        if (escolha == 0) percepcao = 3;
        if (escolha == 2) percepcao = 1;

        // 2. Cria o Personagem com a dificuldade escolhida
        Personagem personagem = new Personagem(percepcao);
        
        // 3. Sorteia e inicializa o tabuleiro
        Random gerador = new Random();
        int random = gerador.nextInt(3) + 1;
        
        Tabuleiro tabuleiro = new Tabuleiro("tabuleiro" + random + ".txt", personagem); 
        // Nota: Certifique-se de passar os parâmetros corretos exigidos pelo construtor do seu Tabuleiro!
        
        GerenciadorMovimento gerenciador = new GerenciadorMovimento(tabuleiro);
        
        // 4. Inicia a janela do jogo completa
        EventQueue.invokeLater(() -> {
            JanelaJogo tela = new JanelaJogo(tabuleiro, gerenciador);
            tela.setVisible(true);
        });
    }
}