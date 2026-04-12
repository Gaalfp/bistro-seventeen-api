package br.com.techchallenge.bistro.seventeen.adapter.input.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record CadastrarUsuarioRequest(

        @NotBlank(message = "Nome Obrigatório para realizar o cadastro.")
        String nome,

        @NotBlank(message = "O CPF informado é inválido.")
        @CPF
        String cpf,

        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail precisa ser válido para cadastro.")
        String email,

        @NotBlank(message = "Login Obrigatório para realizar o cadastro.")
        String login,

        @NotBlank(message = "Senha obrigatória para realizar o cadastro")
        String senha,

        @NotBlank(message = "Endereço é obrigatório para realizar do cadastro.")
        String endereco,

        @NotBlank(message = "Role é obrigatória.")
        @Pattern(regexp = "DONO_RESTAURANTE|CLIENTE", message = "Role deve ser DONO_RESTAURANTE ou CLIENTE.")
        String role
) {
}
