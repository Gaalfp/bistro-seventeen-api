package br.com.techchallenge.bistro.seventeen.port.output;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryOutputPort {

    Optional<Usuario> buscarPorNome(String nome);
}
