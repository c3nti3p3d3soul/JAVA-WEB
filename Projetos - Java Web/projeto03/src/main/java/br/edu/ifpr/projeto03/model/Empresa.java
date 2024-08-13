package br.edu.ifpr.projeto03.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String cidade;
    @NotNull
    @NotBlank
    private String estado;
    @OneToMany(mappedBy = "empresa")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<Setor> setores;
    @OneToMany(mappedBy = "empresa")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<Funcionario> funcionarios;


    public Empresa() {
    }


    

    public Empresa(String nome, String cidade, String estado) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }




    public Empresa(String nome, String cidade, String estado, Set<Setor> setores, Set<Funcionario> funcionarios) {
       
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.setores = setores;
        this.funcionarios = funcionarios;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Setor> getSetores() {
        return this.setores;
    }

    public void setSetores(Set<Setor> setores) {
        this.setores = setores;
    }

    public Set<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
 

}
