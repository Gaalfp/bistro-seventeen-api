package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCriarRequest(

        @NotBlank(message = "Nome Obrigatório para realizar o cadastro.")
        String nome,

        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail precisa ser válido para cadastro.")
        String email,

        @NotBlank(message = "Login Obrigatório para realizar o cadastro.")
        String login,

        @NotBlank(message = "Senha obrigatória para realizar o cadastro")
        String senha,

        String endereco,

        String role

) {
}
