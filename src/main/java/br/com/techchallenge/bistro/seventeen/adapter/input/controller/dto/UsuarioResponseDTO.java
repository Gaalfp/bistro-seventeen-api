package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nome,
        String email,
        String login,
        String endereco,
        TipoUsuario tipo,
        @JsonProperty("ultima_alteracao")
        LocalDateTime dataUltimaAlteracao
) { }

