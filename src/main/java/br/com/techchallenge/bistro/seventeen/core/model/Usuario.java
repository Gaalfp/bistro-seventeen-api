package br.com.techchallenge.bistro.seventeen.core.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private TipoUsuario tipo;
    private LocalDateTime dataUltimaAlteracao;
    private String status;
    private Boolean ativo;


    public Usuario () {}

    public Usuario(String nome, String email, String login, String senha, String endereco, TipoUsuario tipo) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public Usuario(String nome, String email, String login, String senha, String endereco, TipoUsuario tipo
        , LocalDateTime dataUltimaAlteracao, String status, Boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.tipo = tipo;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.status = status;
        this.ativo = ativo;
    }

    public Usuario(UUID id, String nome, String email, String login, String senha, String endereco, TipoUsuario tipo
            , LocalDateTime dataUltimaAlteracao , String status, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.tipo = tipo;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.status = status;
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(endereco, usuario.endereco) && tipo == usuario.tipo && Objects.equals(dataUltimaAlteracao, usuario.dataUltimaAlteracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, login, senha, endereco, tipo, dataUltimaAlteracao);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tipo=" + tipo +
                ", dataUltimaAlteracao=" + dataUltimaAlteracao +
                '}';
    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
