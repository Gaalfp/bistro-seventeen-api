package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "O campo login é obrigatório.")
        String login,

        @NotBlank(message = "O campo senha é obrigatório.")
        String senha
) {
}