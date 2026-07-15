/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.itens;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class Inventario {
    private List<Item> itens;
    private final int CAPACIDADE = 5; // Caso queira limitar o tamanho da mochila

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public boolean adicionarItem(Item novoItem) {
        if (itens.size() < CAPACIDADE) {
            itens.add(novoItem);
            return true;
        }
        return false;
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }
    
    public List<Item> getItens() {
        return itens;
    }
    
    public Item buscarItem(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }
    
    public Arma getArma() {
        return (Arma) buscarItem("arma");
    }
    
    public Bastao getBastao() {
        return (Bastao) buscarItem("bastao");
    }
    
    public Kit getKit() {
        return (Kit) buscarItem("kit");
    }
    
    public void restauraInventario() {
        this.itens.clear();
    }
}