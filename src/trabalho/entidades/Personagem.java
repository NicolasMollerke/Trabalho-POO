/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import trabalho.interfaces.Ataque;
import trabalho.modelo.ElementoDinamico;
import trabalho.itens.Inventario;
import trabalho.interfaceGrafica.JanelaJogo;
import trabalho.interfaces.Movel;
import trabalho.itens.Arma;
import trabalho.itens.Caixa;
import trabalho.itens.Item;
import trabalho.itens.Kit;
import trabalho.mecanicas.Combate;
import trabalho.mecanicas.GerenciadorMovimento;
import trabalho.modelo.Elemento;
import trabalho.modelo.Tabuleiro;

/**
 *
 * @author nicol
 */
public class Personagem extends ElementoDinamico implements Ataque{
    private int percepcao;
    private int saude;
    private Inventario inventario;
    
    public Personagem (int percepcao) {
        super(0,0);
        this.percepcao = percepcao;
        this.saude = 5;
        this.inventario = new Inventario();
    }
    
    public String getSimbolo() { 
        return "P"; 
    }
    
    public boolean estaVivo () {
        return saude > 0;
    }
   
    public Inventario getInventario () {
        return inventario;
    }
    
    public int getPercecao() {
        return percepcao;
    }
    
    public int getSaude() {
        return saude;
    }
    
    public void levarDano(int dano) {
        Random gerador = new Random();
        int dado = gerador.nextInt(3) + 1;
        
        if (dado <= percepcao) {
            JanelaJogo.log("Voce desviou do ataque!");
        } else {
            this.saude = this.saude - dano;
            JanelaJogo.log("🦖 O Dinossauro atacou e te deu " + dano + " de dano!");
        }
    }
    
    /*public int[] mover() {
        int[] coordenadas = new int[2]; 
        
        Scanner teclado = new Scanner(System.in);

        System.out.println("Para onde voce quer se mover?");
        System.out.println("1.^ Cima");
        System.out.println("2.< Esquerda");
        System.out.println("3.> Direita");
        System.out.println("4.v Baixo"); 

        int direcao = teclado.nextInt();

        int linhaNova = this.linha;
        int colunaNova = this.coluna;

        switch (direcao) {
            case 1:
                linhaNova--; // Cima
                break;
            case 2:
                colunaNova--; // Esquerda
                break;
            case 3:
                colunaNova++; // Direita
                break;
            case 4:
                linhaNova++;  // Baixo
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
        coordenadas[0] = linhaNova;
        coordenadas[1] = colunaNova;
        
        return coordenadas;
    }*/
    
    public void moverJogador(int novaLinha, int novaColuna, GerenciadorMovimento gerenciador) {
        Tabuleiro tabuleiro = gerenciador.getTabuleiro();
        Elemento[][] matriz = tabuleiro.getMatriz();

            if (!gerenciador.posicaoValida(novaLinha, novaColuna, (ElementoDinamico) this)) {
                JanelaJogo.log("Limite do mapa atingido! Tente novamente.");
                return;
            }

            Elemento destino = matriz[novaLinha][novaColuna];

            if (destino == null) {
                gerenciador.atualizarPosicao((ElementoDinamico)this, novaLinha, novaColuna);
            } 

            else if (destino instanceof Caixa) {
                JanelaJogo.log("\n[!] Voce pisou em uma caixa de suprimentos 'X'!");
                Caixa caixa = (Caixa) destino;
                Item itemSurpresa = caixa.abrirCaixa(tabuleiro);

                gerenciador.processarItemCaixa(this, itemSurpresa, novaLinha, novaColuna, tabuleiro.getMatriz());
            } 

            else if (destino instanceof Dinossauro) {
                Dinossauro dinossauro = (Dinossauro) destino;
                
                String nomeDino = dinossauro.getNome();
                JanelaJogo.log("Voce iniciou um combate com " + nomeDino);

                Combate combate = new Combate(this, dinossauro);
                combate.iniciadoPorJogador(tabuleiro);
            } 
            else {
                JanelaJogo.log("Caminho bloqueado por uma parede! Tente outra direção.");
            }
    }
    
    public void usarKit () {
        if (inventario.getKit() != null) {
            int cura = inventario.getKit().curar(this);
            this.saude += cura;
            inventario.removerItem(inventario.getKit());
        } else {
            JanelaJogo.log("\n[!] Voce nao possui nenhum Kit Medico no seu inventario!");
        }
    }
    
    public int atacar(ElementoDinamico alvo) {
        java.util.Random gerador = new java.util.Random();
        int dado = gerador.nextInt(6) + 1;
        int dano=0;
        
        if (!(alvo instanceof Rex)) {
            if (dado == 6) {
                JanelaJogo.log("Voce deu um golpe critico! O dinossauro recebeu 2 de dano!");
                dano = 2;
            } else if (dado == 1 || dado == 2) {
                JanelaJogo.log("Voce errou o ataque!");
                dano = 0;
            } else {
                if (alvo instanceof Trodonte) {
                    JanelaJogo.log("Trodonte nao levou dano!");
                } else {
                    dano = 1;
                }
            }
        } else {
            JanelaJogo.log("Rex nao leva dano sem armas!");
        }      
        
        return dano;
    }
    
    public void restaurarPersonagem() {
        this.saude = 5;
        inventario.restauraInventario();
        this.setPosicao(0, 0);
    }
    
    public void carregarJogador(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        
        if (!arquivo.exists()) {
            System.out.println("⚠️ Nenhum save de jogador encontrado em: " + nomeArquivo);
            return;
        }

        try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                
                if (linha.startsWith("SAUDE:")) {
                    int saude = Integer.parseInt(linha.replace("SAUDE:", ""));
                    this.saude = saude;
                }
                
                else if (linha.startsWith("PERCEPCAO:")) {
                    int percepcao = Integer.parseInt(linha.replace("PERCEPCAO:", ""));
                    this.percepcao = percepcao;
                }
                
                else if (linha.startsWith("ITENS:")) {
                    String dadosItens = linha.replace("ITENS:", "");
                    if (!dadosItens.isEmpty()) {
                        String[] itensSalvos = dadosItens.split(",");
                        for (String nomeItem : itensSalvos) {
                            if (nomeItem.equals("bastao")) {
                                this.inventario.adicionarItem(new trabalho.itens.Bastao());
                            } else if (nomeItem.equals("kit")) {
                                this.inventario.adicionarItem(new Kit());
                            } else if (nomeItem.startsWith("arma-")) {
                                int municao = Integer.parseInt(nomeItem.split("-")[1]);
                                this.inventario.adicionarItem(new Arma(municao));
                            }
                        }
                    }
                }
            }
            System.out.println("💾 Dados do jogador carregados com sucesso!");
            
        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar os dados do jogador: " + e.getMessage());
        }
    }
    
    public void salvarPersonagem(String nomeArquivo) {
        try (FileWriter fw = new FileWriter(nomeArquivo);
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println("SAUDE:" + this.saude);
            pw.println("PERCEPCAO:" + this.percepcao);
            
            StringBuilder linhaItens = new StringBuilder("ITENS:");
            List<Item> listaDoInventario = this.inventario.getItens();
            
            for (Item item : listaDoInventario) {
                if (item instanceof Arma) {
                    linhaItens.append("arma-").append(((Arma) item).getMunicao()).append(",");
                } else {
                    linhaItens.append(item.getNome().toLowerCase()).append(",");
                }
            }
            
            String resultado = linhaItens.toString();
            if (resultado.endsWith(",")) {
                resultado = resultado.substring(0, resultado.length() - 1);
            }
            pw.println(resultado);
            
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o jogador: " + e.getMessage());
        }
    }
}
    