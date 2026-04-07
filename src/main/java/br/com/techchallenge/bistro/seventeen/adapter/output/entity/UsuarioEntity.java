package br.com.techchallenge.bistro.seventeen.adapter.output.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario", schema = "app")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 80)
    private String login;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Column(name = "tipo_usuario", nullable = false, length = 30)
    private String tipoUsuario;

    @Column(nullable = false, length = 30)
    private String status;

    @Column(name = "data_ultima_alteracao", nullable = false)
    private LocalDateTime dataUltimaAlteracao;

    @Column(nullable = false)
    private Boolean ativo = true;
}