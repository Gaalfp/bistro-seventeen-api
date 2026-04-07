package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.ValidarLoginInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;

public class ValidarLoginUseCase implements ValidarLoginInputPort {

    private final ConsultarUsuarioPorLoginOutputPort consultarUsuarioPort;
    private final PasswordEncoderOutputPort passwordEncoderPort;

    public ValidarLoginUseCase(ConsultarUsuarioPorLoginOutputPort consultarUsuarioPort,
                               PasswordEncoderOutputPort passwordEncoderPort) {
        this.consultarUsuarioPort = consultarUsuarioPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public String executar(String login, String senhaEmTextoPlano) {
        Usuario usuario = consultarUsuarioPort.buscarPorLogin(login)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoderPort.matches(senhaEmTextoPlano, usuario.getSenhaHash())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return "Login realizado com sucesso!";
    }
}