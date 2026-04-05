package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioCriadoResponse(

        UUID id,
        String nome,
        String email,
        String login,

        @JsonProperty("updated_at")
        LocalDateTime updatedAt
) {

    public static UsuarioCriadoResponse from(Usuario usuario) {
        return new UsuarioCriadoResponse(

                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getDataUltimaAlteracao()
        );
    }
}
