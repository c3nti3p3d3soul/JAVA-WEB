package br.edu.ifpr.restapidelivery.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedido_produto")
public class PedidoProduto implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
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
