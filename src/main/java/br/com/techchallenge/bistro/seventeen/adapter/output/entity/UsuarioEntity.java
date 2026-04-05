package br.com.techchallenge.bistro.seventeen.adapter.output.entity;

import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "usuario", schema = "app")
public class UsuarioEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Column(name = "tipo_usuario", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(nullable = false, length = 30)
    private String status;

    @Column(name = "data_ultima_alteracao", nullable = false)
    private LocalDateTime dataUltimaAlteracao;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false, length = 500)
    private String endereco;


    public UsuarioEntity () {}

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(login, that.login) && Objects.equals(cpf, that.cpf) && Objects.equals(senhaHash, that.senhaHash) && tipoUsuario == that.tipoUsuario && Objects.equals(status, that.status) && Objects.equals(dataUltimaAlteracao, that.dataUltimaAlteracao) && Objects.equals(ativo, that.ativo) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, login, cpf, senhaHash, tipoUsuario, status, dataUltimaAlteracao, ativo, endereco);
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senhaHash='" + senhaHash + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", status='" + status + '\'' +
                ", dataUltimaAlteracao=" + dataUltimaAlteracao +
                ", ativo=" + ativo +
                ", endereco='" + endereco + '\'' +
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

    public String getCpf() {
        return cpf;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public String getEndereco() {
        return endereco;
    }
}
