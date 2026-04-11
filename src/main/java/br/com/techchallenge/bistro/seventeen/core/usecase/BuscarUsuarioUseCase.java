package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

import java.util.List;

public class BuscarUsuarioUseCase implements BuscarUsuarioInputPort {

    private final UsuarioRepositoryOutputPort repository;

    public BuscarUsuarioUseCase(UsuarioRepositoryOutputPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return repository.listarTodos();
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }
}