package br.com.techchallenge.bistro.seventeen.adapter.input.controller.request;

import jakarta.validation.constraints.NotBlank;

public record TrocarSenhaRequest(
        @NotBlank(message = "O campo senhaAtual é obrigatório.")
        String senhaAtual,

        @NotBlank(message = "O campo novaSenha é obrigatório.")
        String novaSenha
) {
}
