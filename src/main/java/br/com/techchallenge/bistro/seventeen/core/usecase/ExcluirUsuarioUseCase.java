package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.adapter.exception.CredenciaisInvalidasException;
import br.com.techchallenge.bistro.seventeen.adapter.exception.EntidadeNaoEncontradaException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.ExcluirUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

import java.util.UUID;

public class ExcluirUsuarioUseCase implements ExcluirUsuarioInputPort {

    private final UsuarioRepositoryOutputPort repository;

    public ExcluirUsuarioUseCase(UsuarioRepositoryOutputPort repository) {
        this.repository = repository;
    }

    @Override
    public void excluir(UUID id) {
        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));

        if (!usuario.isAtivo()) {
            throw new CredenciaisInvalidasException("Usuário já se encontra inativo no sistema.");
        }

        usuario.setAtivo(false);
        repository.salvar(usuario);
    }
}