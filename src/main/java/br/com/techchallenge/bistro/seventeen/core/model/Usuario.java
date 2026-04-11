package br.com.techchallenge.bistro.seventeen.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String login;
    private String cpf;
    private String senhaHash;
    private String tipoUsuario;
    private String status;
    private String endereco;
    private TipoUsuario tipo;
    private LocalDateTime dataUltimaAlteracao;
    private Boolean ativo;

    public Usuario(String nome, String email, String login, String senha, String endereco, TipoUsuario tipo) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senhaHash = senha;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public Usuario(String nome, String email, String login, String senha, String endereco, TipoUsuario tipo
            , LocalDateTime dataUltimaAlteracao, String status, Boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senhaHash = senha;
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
        this.senhaHash = senha;
        this.endereco = endereco;
        this.tipo = tipo;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.status = status;
        this.ativo = ativo;
    }

}