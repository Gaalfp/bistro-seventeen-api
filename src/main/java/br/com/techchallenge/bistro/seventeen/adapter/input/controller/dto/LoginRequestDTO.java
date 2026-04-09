package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "LoginRequestDTO", description = "Objeto contendo as credenciais para autenticação no sistema")
public record LoginRequestDTO(

        @Schema(description = "Login (nome de usuário) cadastrado", example = "admin.master")
        @NotBlank(message = "O campo login é obrigatório.")
        String login,

        @Schema(description = "Senha de acesso em texto plano", example = "123456")
        @NotBlank(message = "O campo senha é obrigatório.")
        String senha
) {
}