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
    private String senhaHash;
    private String endereco;
    private TipoUsuario tipo;
    private LocalDateTime dataUltimaAlteracao;
}