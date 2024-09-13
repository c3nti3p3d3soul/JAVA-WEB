package br.edu.ifpr.restapidelivery.model;

import java.io.Serializable;
import java.util.Objects;

public class PedidoProdutoId implements Serializable {

    private Long pedido;
    private Long produto;

    // Construtor padrão
    public PedidoProdutoId() {}

    public PedidoProdutoId(Long pedido, Long produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    // Getters e Setters
    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    // Sobrescrever equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoProdutoId that = (PedidoProdutoId) o;
        return Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}
