package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.adapter.exception.EntidadeNaoEncontradaException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

public class BuscarUsuarioUseCase implements BuscarUsuarioInputPort {

    private final UsuarioRepositoryOutputPort repository;

    public BuscarUsuarioUseCase(UsuarioRepositoryOutputPort repository) {
        this.repository = repository;
    }

    @Override
    public Usuario buscarPorNome(String nome) {
        return repository.buscarPorNome(nome)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com nome: " + nome + " não encotrado!"));
    }
}
