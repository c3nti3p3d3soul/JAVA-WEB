package br.edu.ifpr.projeto02.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String login;
    @NotNull
    @Size(min = 8, message= "Senha deve conter no mínimo de 8 caracteres")
    private String senha;
    @NotNull
    private String chave;
    

        public Usuario() {
    }

        public Usuario( String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;

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

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "{" +
            "id='" + getId() + "'"+
            "nome='" + getNome() + "'" +
            "login='" + getLogin() + "'"+
            "senha='" + getSenha() + "'"+
            "}";
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

}
