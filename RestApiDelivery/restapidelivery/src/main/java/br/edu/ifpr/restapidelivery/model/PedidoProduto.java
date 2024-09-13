package br.edu.ifpr.restapidelivery.model;

import jakarta.persistence.*;

@Entity
@IdClass(PedidoProdutoId.class) // Indica que PedidoProdutoId é a chave composta
public class PedidoProduto {

    @Id
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // Construtor padrão
    public PedidoProduto() {}

    // Construtor com parâmetros
    public PedidoProduto(Pedido pedido, Produto produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    // Getters e Setters
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
