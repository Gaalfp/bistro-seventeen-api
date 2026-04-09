package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.ValidarLoginInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

public class ValidarLoginUseCase implements ValidarLoginInputPort {

    private final UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;
    private final PasswordEncoderOutputPort passwordEncoderPort;

    public ValidarLoginUseCase(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort, PasswordEncoderOutputPort passwordEncoderPort) {
        this.usuarioRepositoryOutputPort = usuarioRepositoryOutputPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }


    @Override
    public String executar(String login, String senhaEmTextoPlano) {
        Usuario usuario = usuarioRepositoryOutputPort.buscarPorLogin(login)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoderPort.matches(senhaEmTextoPlano, usuario.getSenhaHash())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return "Login realizado com sucesso!";
    }
}