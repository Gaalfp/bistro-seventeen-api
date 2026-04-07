package br.com.techchallenge.bistro.seventeen.port.output;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import java.util.Optional;

public interface ConsultarUsuarioPorLoginOutputPort {
    Optional<Usuario> buscarPorLogin(String login);
}