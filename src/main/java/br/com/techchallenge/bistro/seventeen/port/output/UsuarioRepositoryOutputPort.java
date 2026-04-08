package br.com.techchallenge.bistro.seventeen.port.output;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryOutputPort {

    Optional<Usuario> buscarPorId(UUID id);

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorLogin(String login);

    Optional<Usuario> buscarPorCpf(String cpf);
}
