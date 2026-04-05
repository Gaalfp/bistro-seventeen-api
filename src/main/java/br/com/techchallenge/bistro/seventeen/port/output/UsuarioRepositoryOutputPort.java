package br.com.techchallenge.bistro.seventeen.port.output;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;

public interface UsuarioRepositoryOutputPort {

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Usuario save(Usuario usuario);
}
