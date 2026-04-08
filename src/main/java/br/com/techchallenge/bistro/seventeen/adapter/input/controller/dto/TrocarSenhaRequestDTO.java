package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record TrocarSenhaRequestDTO(
        @NotBlank(message = "O campo senhaAtual é obrigatório.")
        String senhaAtual,

        @NotBlank(message = "O campo novaSenha é obrigatório.")
        String novaSenha
) {
}
