package trabalho; 

import java.io.File;
import java.io.FileNotFoundException; // Import necessário para tratar erro de arquivo sumido
import java.util.Scanner;

public class Tabuleiro {
    private Elemento[][] matriz;
    private int tamanho;
    
    public Tabuleiro (String caminhoArquivo, Personagem personagem) {
        this.tamanho = 10; 
        this.matriz = new Elemento[tamanho][tamanho]; 
        
        this.lerTabuleiro(caminhoArquivo, personagem);
    }
    
    private void lerTabuleiro (String caminhoArquivo, Personagem personagem) {
        try {
            File arquivo = new File(caminhoArquivo);
            Scanner scanner = new Scanner(arquivo);
            
            for (int i = 0; i < tamanho; i++){
                for (int j = 0; j < tamanho; j++){
                    if (scanner.hasNext()) {
                        String caractere = scanner.next();
                        
                        switch (caractere) {
                            case "P": 
                                this.matriz[i][j] = personagem;
                                break;
                            case "#":
                                this.matriz[i][j] = new Parede();
                                break;
                            case "T":
                                this.matriz[i][j] = new Trodonte();
                                break;
                            case "V":
                                this.matriz[i][j] = new Velociraptor();
                                break;
                            case "C":
                                this.matriz[i][j] = new Compsognato();
                                break;
                            case "X":
                                this.matriz[i][j] = new Kit(); // Perfeito! Mudou para Kit
                                break;
                            case "R":
                                this.matriz[i][j] = new Rex();
                                break;
                            default:
                                this.matriz[i][j] = null; // Garante que o "." vire espaço vazio
                                break;
                        }
                    }
                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Não foi possível encontrar o arquivo " + caminhoArquivo);
        }
    }
    
    public void mostrarTabuleiro () {
        System.out.print("  "); 
        for (int col = 1; col <= 10; col++) System.out.print(col + " ");
        System.out.println();

        for (int i = 0; i < tamanho; i++){
            System.out.print((char)('A' + i) + "| "); 

            for (int j = 0; j < tamanho; j++){
                Elemento atual = this.matriz[i][j];
            
                if (atual == null) {
                    System.out.print(". "); 
                } else {
                   System.out.print(atual.getSimbolo() + " ");
                }
            }
            System.out.println();
        }
    }
}