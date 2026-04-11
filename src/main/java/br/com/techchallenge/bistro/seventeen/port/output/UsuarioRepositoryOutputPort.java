package br.com.techchallenge.bistro.seventeen.port.output;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryOutputPort {
    Optional<Usuario> buscarPorId(UUID id);

    Optional<Usuario> buscarPorNome(String nome);

    void salvar(Usuario usuario);


    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    /*Criei este save para não quebrar o salvar void*/
    Usuario save(Usuario usuario);
}
