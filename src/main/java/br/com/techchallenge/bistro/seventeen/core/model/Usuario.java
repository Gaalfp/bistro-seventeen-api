package br.com.techchallenge.bistro.seventeen.core.model;

import java.time.LocalDateTime;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private TipoUsuario tipo;
    private LocalDateTime dataUltimaAlteracao;
}
