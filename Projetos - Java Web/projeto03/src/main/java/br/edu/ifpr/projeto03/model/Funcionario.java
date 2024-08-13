package br.edu.ifpr.projeto03.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    @Email(message = "E-mail não é valido")
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "empresa_id")
    private Empresa empresa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "setor_id")
    private Setor setor;

  


    
    public Funcionario() {
    }

    public Funcionario(String nome, String email, Empresa empresa, Setor setor) {
      
        this.nome = nome;
        this.email = email;
        this.empresa = empresa;
        this.setor = setor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
