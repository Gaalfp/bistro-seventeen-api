package br.com.techchallenge.bistro.seventeen.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String login;
    private String cpf;
    private String senhaHash;
    private String endereco;
    private TipoUsuario tipoUsuario;
    private LocalDateTime dataUltimaAlteracao;
    private boolean ativo;
}