package br.com.techchallenge.bistro.seventeen.adapter.output.entity;

import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false, length = 30)
    private TipoUsuario tipoUsuario;

    @Column(nullable = false, length = 30)
    private String status;

    @Column(name = "data_ultima_alteracao", nullable = false)
    private LocalDateTime dataUltimaAlteracao;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false, length = 500)
    private String endereco;

    public UsuarioEntity() {}

    public UsuarioEntity(UUID id, String nome, String email, String login, String cpf, String senhaHash,
                         TipoUsuario tipoUsuario, String status, LocalDateTime dataUltimaAlteracao, Boolean ativo, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.tipoUsuario = tipoUsuario;
        this.status = status;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.ativo = ativo;
        this.endereco = endereco;
    }


}