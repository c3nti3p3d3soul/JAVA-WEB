package br.edu.ifpr.restapidelivery.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pedido")
    private Set<PedidoProduto> pedidoProdutos = new HashSet<>(); // Inicializando a coleção

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Float valor;

    private String entrega;

    // Construtor padrão
    public Pedido() {
        this.pedidoProdutos = new HashSet<>(); // Inicializa a coleção no construtor
    }

    // Construtor com parâmetros
    public Pedido(Long id, LocalDateTime data, Usuario usuario, Float valor, String entrega) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.valor = valor;
        this.entrega = entrega;
        this.pedidoProdutos = new HashSet<>(); // Inicializa a coleção no construtor com parâmetros
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public Set<PedidoProduto> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(Set<PedidoProduto> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }
}
