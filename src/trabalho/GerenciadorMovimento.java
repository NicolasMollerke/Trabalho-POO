/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

/**
 *
 * @author nicol
 */
public class GerenciadorMovimento {
    private Tabuleiro tabuleiro;
    private int tamanho;

    public GerenciadorMovimento(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.tamanho = tabuleiro.getTamanho();
    }
    
        
    public void moverDinossauros () {
        java.util.ArrayList<Dinossauro> movidos = new java.util.ArrayList<>();
        Elemento[][] matriz = tabuleiro.getMatriz();
        
        for (int i=0; i < tamanho; i++) {
            for (int j=0; j < tamanho; j++) {
                Elemento atual = matriz[i][j];
                
                if (atual instanceof Dinossauro) {
                    Dinossauro dinossauro = (Dinossauro) atual;
                    
                    if(!movidos.contains(dinossauro) && dinossauro instanceof  Movel) {
                        Movel dinoMovel = (Movel) dinossauro; //objeto real que está aqui dentro implementa a interface Movel
                        
                        movidos.add(dinossauro);
                        if (dinoMovel instanceof Velociraptor) {
                            for (int passo = 0; passo < 2; passo++) {
                                realizarMovimento(dinoMovel, matriz, tamanho);
                            }
                        } 
                        else {
                            realizarMovimento(dinoMovel, matriz, tamanho);
                        }
                        
                    }
                }
            }
        }
        
    }
    
    public void moverJogador(Personagem jogador) {
        Elemento[][] matriz = tabuleiro.getMatriz();
        boolean mover = false;

        while (!mover) {
            int[] coordenadas = jogador.mover();
            int novaLinha = coordenadas[0];
            int novaColuna = coordenadas[1];

            if (!posicaoValida(novaLinha, novaColuna, (ElementoDinamico) jogador)) {
                System.out.println("Limite do mapa atingido! Tente novamente.");
                continue;
            }

            Elemento destino = matriz[novaLinha][novaColuna];

            if (destino == null) {
                atualizarPosicao((ElementoDinamico)jogador, novaLinha, novaColuna);
                mover = true;
            } 

            else if (destino instanceof Caixa) {
                System.out.println("\n[!] Voce pisou em uma caixa de suprimentos 'X'!");
                Caixa caixa = (Caixa) destino;
                Item itemSurpresa = caixa.abrirCaixa(tabuleiro);

                processarItemCaixa(jogador, itemSurpresa, novaLinha, novaColuna, tabuleiro.getMatriz());
                mover = true;   
            } 

            else if (destino instanceof Dinossauro) {
                Dinossauro dinossauro = (Dinossauro) destino;
                System.out.println("Voce iniciou um combate com um dinossauro!");

                Combate combate = new Combate(jogador, dinossauro);
                combate.iniciadoPorJogador(tabuleiro);
                mover = true;
            } 
            else {
                System.out.println("Caminho bloqueado por uma parede! Tente outra direção.");
            }
        }
    }
    
    private void processarItemCaixa(Personagem jogador, Item itemSurpresa, int novaLinha, int novaColuna, Elemento[][] matriz) {
        Inventario inventario = jogador.getInventario(); 

        if (itemSurpresa instanceof Arma) {
            if (inventario.getArma() == null){
                inventario.adquireArma((Arma) itemSurpresa);
                System.out.println("Parabens! Voce adquiriu uma Arma de Dardos!");
            } else {
                inventario.getArma().ganhaMunicao();
                System.out.println("Parabens! Voce adquiriu municao para sua Arma!");
            }

            Compsognato compso = new Compsognato(novaLinha, novaColuna);
            tabuleiro.adicionaDinossauro(); 
            matriz[novaLinha][novaColuna] = compso;

            System.out.println("Um Compsognato surpresa atacou voce!");
            Combate combate = new Combate(jogador, compso);
            combate.iniciadoPorDinossauro(tabuleiro);

        } else if (itemSurpresa instanceof Bastao){
            inventario.adquireBastao((Bastao) itemSurpresa);
            System.out.println("Parabens! Voce adquiriu um Bastao de Choque");
            atualizarPosicao((ElementoDinamico)jogador, novaLinha, novaColuna);

        } else if (itemSurpresa instanceof Kit){
            inventario.adquireKit((Kit) itemSurpresa);
            System.out.println("Parabens! Voce adquiriu um Kit Medico");
            atualizarPosicao((ElementoDinamico)jogador, novaLinha, novaColuna);
        }
    }
    
    
    public void realizarMovimento(Movel elemento, Elemento[][] matriz, int tamanho) {
        boolean mover = false;
        int[] coordenadas = null;
        int tentativas = 0; 

        while(!mover && tentativas < 10) {
            coordenadas = elemento.mover();
            mover = posicaoValida(coordenadas[0], coordenadas[1], (ElementoDinamico) elemento);
            tentativas++;
        }

        if (!mover) {
            return; 
        }

        int novaLinha = coordenadas[0];
        int novaColuna = coordenadas[1];

        Elemento destino = matriz[novaLinha][novaColuna];

        if (destino instanceof Personagem) {
            Personagem jogador = (Personagem) destino;
            System.out.println("\n[!] Um dinossauro emboscou voce!");

            Combate combate = new Combate(jogador, (Dinossauro) elemento);
            combate.iniciadoPorDinossauro(tabuleiro);
        } 
        else {
            atualizarPosicao((ElementoDinamico) elemento, novaLinha, novaColuna);
        }
    }
    
    public boolean posicaoValida(int x, int y, Elemento elemento) {
        if (x < 0 || x >= this.tamanho || y < 0 || y >= this.tamanho) {
            return false;
        }

        Elemento destino = tabuleiro.getMatriz()[x][y];

        if (elemento instanceof Personagem) {
            return true; 
        }

        // 3. REGRAS PARA O DINOSSAURO
        if (elemento instanceof Dinossauro) {
            return destino == null || destino instanceof Personagem;
        }

        return false;
    }
    
    public void atualizarPosicao (ElementoDinamico elemento, int x, int y) {
        Elemento[][] matriz = tabuleiro.getMatriz();
        
        matriz[elemento.getLinha()][elemento.getColuna()] = null;
        
        elemento.setPosicao(x, y);
        
        matriz[x][y] = elemento;
    }
}
