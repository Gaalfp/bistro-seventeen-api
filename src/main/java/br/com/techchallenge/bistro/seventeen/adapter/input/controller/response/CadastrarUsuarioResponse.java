package br.com.techchallenge.bistro.seventeen.adapter.input.controller.response;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import java.util.UUID;

public record CadastrarUsuarioResponse(
        UUID id,
        String nome,
        String email,
        String login,
        String cpf,
        String endereco,
        String role
) {
    public static CadastrarUsuarioResponse from(Usuario usuario) {
        return new CadastrarUsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getCpf(),
                usuario.getEndereco(),
                usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().name() : null
        );
    }
}