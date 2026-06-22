package trabalho; 

import java.io.File;
import java.io.FileNotFoundException; // Import necessário para tratar erro de arquivo sumido
import java.util.Scanner;

public class Tabuleiro {
    private Elemento[][] matriz;
    private int tamanho;
    private Personagem personagem;
    private boolean debug;
    
    public Tabuleiro (String caminhoArquivo, Personagem personagem) {
        this.tamanho = 10; 
        this.matriz = new Elemento[tamanho][tamanho]; 
        this.personagem = personagem;
        
        this.lerTabuleiro(caminhoArquivo);
    }
    
    public Elemento[][] getMatriz() {
        return matriz;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    public Personagem getPersonagem() {
        return personagem;
    }
    
    private void lerTabuleiro (String caminhoArquivo) {
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
                                personagem.setPosicao(i, j);
                                break;
                            case "#":
                                this.matriz[i][j] = new Parede(i, j);
                                break;
                            case "T":
                                this.matriz[i][j] = new Trodonte(i, j);
                                break;
                            case "V":
                                this.matriz[i][j] = new Velociraptor(i, j);
                                break;
                            case "C":
                                this.matriz[i][j] = new Compsognato(i, j);
                                break;
                            case "X":
                                this.matriz[i][j] = new Kit(i, j);
                                break;
                            case "R":
                                this.matriz[i][j] = new Rex(i, j);
                                break;
                            default:
                                this.matriz[i][j] = null;
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
        int x = personagem.linha;
        int y = personagem.coluna;
        boolean visivel[][] = this.campoDeVisao(x, y);
        
        System.out.print("  ");
        for (int col = 1; col <= 10; col++) System.out.print(col + " ");
        System.out.println();
        
        if (!debug) {
            for (int i = 0; i < tamanho; i++){
                System.out.print((char)('A' + i) + "| "); 

                for (int j = 0; j < tamanho; j++){
                    Elemento atual = this.matriz[i][j];

                    if (atual == null && visivel[i][j]) {
                        System.out.print(". "); 
                    } else if (atual != null && visivel[i][j]) {
                       System.out.print(atual.getSimbolo() + " ");
                    } else {
                        System.out.print("? "); 
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
    
    public void moverDinossauros () {
        java.util.ArrayList<Dinossauro> movidos = new java.util.ArrayList<>();
        
        for (int i=0; i < tamanho; i++) {
            for (int j=0; j < tamanho; j++) {
                Elemento atual = this.matriz[i][j];
                
                if (atual instanceof Dinossauro) {
                    Dinossauro dinossauro = (Dinossauro) atual;
                    
                    if(!movidos.contains(dinossauro)) {
                        dinossauro.mover(this);
                        
                        movidos.add(dinossauro);
                    }
                }

            }
        }
        
    }
    
    public void ativarDebug() {
        this.debug = true;
    }
    
}