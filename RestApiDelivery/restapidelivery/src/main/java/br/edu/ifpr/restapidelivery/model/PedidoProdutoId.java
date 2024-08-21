package br.edu.ifpr.restapidelivery.model;

import java.io.Serializable;
import java.util.Objects;

public class PedidoProdutoId implements Serializable {

    private Long pedidoId;
    private Long produtoId;

    // Getters e Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoProdutoId that = (PedidoProdutoId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
               Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, produtoId);
    }
}
