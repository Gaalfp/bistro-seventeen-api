package br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto;

import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AtualizarUsuarioDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O login é obrigatório")
        String login,

        @NotBlank(message = "O endereço é obrigatório")
        String endereco,

        @NotNull(message = "O tipo de usuário é obrigatório")
        TipoUsuario tipo,

        @NotBlank(message = "O status de usuário é obrigatório")
        String status
) { }
