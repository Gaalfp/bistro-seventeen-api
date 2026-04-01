package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.port.input.ConsultarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

public class ConsultarUsuarioUseCase implements ConsultarUsuarioInputPort {

    private final UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;

    public ConsultarUsuarioUseCase(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort) {
        this.usuarioRepositoryOutputPort = usuarioRepositoryOutputPort;
    }


    @Override
    public String validarUsuario(String email, String senha) {
        return "";
    }
}
