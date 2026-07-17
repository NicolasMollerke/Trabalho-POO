package trabalho.modelo; 

import trabalho.entidades.Trodonte;
import trabalho.entidades.Rex;
import trabalho.entidades.Compsognato;
import trabalho.entidades.Personagem;
import trabalho.entidades.Velociraptor;
import java.io.File;
import java.io.FileNotFoundException; // Import necessário para tratar erro de arquivo sumido
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import trabalho.itens.Caixa;
import trabalho.interfaceGrafica.JanelaJogo;
import trabalho.Parede;
import trabalho.itens.Item;
import java.util.List;
import java.util.ArrayList;
import trabalho.mecanicas.GerenciadorMovimento;
import trabalho.entidades.Dinossauro;
import trabalho.itens.Arma;

public class Tabuleiro {
    private Elemento[][] matriz;
    private int tamanho;
    private Personagem personagem;
    private boolean debug;
    private int numDinossauros;
    private ArrayList<String> itensCaixas;
    private String nomeTabuleiro;
    
    public Tabuleiro (String caminhoArquivo, Personagem personagem) {
        this.tamanho = 20; 
        this.matriz = new Elemento[tamanho][tamanho]; 
        this.personagem = personagem;
        this.numDinossauros = 0;
        
        this.itensCaixas = new ArrayList<>();
        this.itensCaixas.add("bastao");
        this.itensCaixas.add("kit");
        this.itensCaixas.add("arma");
        this.itensCaixas.add("arma");
        this.nomeTabuleiro = caminhoArquivo;
        
        Collections.shuffle(this.itensCaixas);
        
        this.lerTabuleiro(caminhoArquivo);
    }
    
    public Elemento[][] getMatriz() {
        return matriz;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    public String getNomeTabuleiro () {
        return nomeTabuleiro;
    }
    
    public Personagem getPersonagem() {
        return personagem;
    }
    
    public void adicionaDinossauro() {
        this.numDinossauros++;
    }
    
    public void removeDinossauro() {
        this.numDinossauros--;
    }
    
    public boolean semDinossauros() {
        return numDinossauros <= 0;
    }
    
    private void lerTabuleiro(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        
        try (Scanner scanner = new Scanner(arquivo)) {
            
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    if (scanner.hasNext()) {
                        String caractere = scanner.next();
                        
                        switch (caractere) {
                            case "P": 
                                this.matriz[i][j] = personagem;
                                personagem.setPosicao(i, j);
                                break;
                            case "#":
                                this.matriz[i][j] = new Parede(i, j);
                                break;
                            case "T":
                                this.matriz[i][j] = new Trodonte(i, j, personagem);
                                this.numDinossauros++;
                                break;
                            case "V":
                                this.matriz[i][j] = new Velociraptor(i, j);
                                this.numDinossauros++;
                                break;
                            case "C":
                                this.matriz[i][j] = new Compsognato(i, j);
                                this.numDinossauros++;
                                break;
                            case "X":
                                this.matriz[i][j] = new Caixa(i, j);
                                break;
                            case "R":
                                this.matriz[i][j] = new Rex(i, j);
                                this.numDinossauros++;
                                break;
                            default:
                                this.matriz[i][j] = null;
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            JanelaJogo.log("Erro ao processar a leitura do tabuleiro: " + e.getMessage());
        }
    }
    
    public synchronized void mostrarTabuleiro () {
        int x = personagem.linha;
        int y = personagem.coluna;
        boolean visivel[][] = this.campoDeVisao(x, y);
        
        System.out.print("   ");
        for (int col = 1; col <= tamanho; col++) System.out.printf("%-3d", col);
        System.out.println();
        
        if (!debug) {
            for (int i = 0; i < tamanho; i++){
                System.out.print((char)('A' + i) + "| "); 

                for (int j = 0; j < tamanho; j++){
                    Elemento atual = this.matriz[i][j];

                    if (atual == null && visivel[i][j]) {
                        System.out.printf("%-3s", "."); 
                    } else if (atual != null && visivel[i][j]) {
                      System.out.printf("%-3s", atual.getSimbolo());
                    } else {
                        System.out.printf("%-3s", "?"); 
                    }
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < tamanho; i++){
                System.out.print((char)('A' + i) + "| "); 

                for (int j = 0; j < tamanho; j++){
                    Elemento atual = this.matriz[i][j];

                    if (atual == null) {
                        System.out.print(". "); 
                    }else {
                       System.out.print(atual.getSimbolo() + " ");
                    }
                }
                System.out.println();
            }
        }
        
    }
    
    public boolean[][] campoDeVisao (int x, int y) {
        boolean[][] visivel = new boolean [tamanho][tamanho];
        visivel[x][y] = true;
        
        for (int l=x+1; l < tamanho; l++){
            visivel[l][y] = true;
            if (matriz[l][y] != null) {
                visivel[l][y] = true;
                break;
            }
        }
        for (int o=x-1; o >= 0; o--){
            visivel[o][y] = true;
            if (matriz[o][y] != null) {
                visivel[o][y] = true;
                break;
            }
        }
        
        for (int n=y-1; n >= 0; n--){
            visivel[x][n] = true;
            if (matriz[x][n] != null) {
                visivel[x][n] = true;
                break;
            }
        }
        
        for (int s=y+1; s < tamanho; s++){
            visivel[x][s] = true;
            if (matriz[x][s] != null) {
                visivel[x][s] = true;
                break;
            }
        }
        
        return visivel;
    }
    
    public String sortearItem() {
        if (this.itensCaixas.isEmpty()) {
            return "arma"; 
        }
        return this.itensCaixas.remove(0); //pega o primeiro e o remove da lista, comoportamento de pilha
    }
    
    public boolean getDebug () {
        return debug;
    }
    
    public void ativarDebug() {
        this.debug = true;
    }
    
    public void desativarDebug() {
        this.debug = false;
    }
  
    public void salvarMapa(String nomeArquivo) {
        try (FileWriter fw = new FileWriter(nomeArquivo);
             PrintWriter pw = new PrintWriter(fw)) {
            
            for (int i = 0; i < tamanho; i++) {
                StringBuilder linhaTexto = new StringBuilder();
                for (int j = 0; j < tamanho; j++) {
                    Elemento atual = matriz[i][j];
                    if (atual == null) {
                        linhaTexto.append(". ");
                    } else {
                        linhaTexto.append(atual.getSimbolo()).append(" ");
                    }
                }
                pw.println(linhaTexto.toString().trim());
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o mapa: " + e.getMessage());
        }
    }
    
    public void iniciarThreadsDinossauros(GerenciadorMovimento gerenciador) {
        Elemento[][] matriz = this.getMatriz();
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                Elemento elemento = matriz[i][j];
                
                //procura o dinossauro
                if (elemento instanceof Runnable) {
                    
                    Dinossauro dino = (Dinossauro) elemento;
                    
                    dino.setGerenciador(gerenciador);
                    //inicia a thread
                    Thread threadDino = new Thread((Runnable) elemento);
                    threadDino.start();
                }
            }
        }
    }
    
    
}