package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.exception.RecursoEmConflitoException;
import br.com.techchallenge.bistro.seventeen.core.exception.RecursoJaExisteException;
import br.com.techchallenge.bistro.seventeen.core.exception.UsuarioNaoEncontradoException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.AtualizarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

import java.time.LocalDateTime;

public class AtualizarUsuarioUseCase implements AtualizarUsuarioInputPort {

    private final UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;

    public AtualizarUsuarioUseCase(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort) {
        this.usuarioRepositoryOutputPort = usuarioRepositoryOutputPort;
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepositoryOutputPort.buscarPorId(usuario.getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(
                        "Usuário com ID " + usuario.getId() + " não encontrado"
                ));

        if (!usuarioExistente.isAtivo()) {
            throw new RecursoEmConflitoException("Não é possível atualizar os dados de um usuário inativo.");
        }

        if (!usuarioExistente.getEmail().equals(usuario.getEmail())) {
            usuarioRepositoryOutputPort.buscarPorEmail(usuario.getEmail())
                    .ifPresent(u -> {
                        throw new RecursoJaExisteException("Email já cadastrado no sistema");
                    });
        }

        if (!usuarioExistente.getLogin().equals(usuario.getLogin())) {
            usuarioRepositoryOutputPort.buscarPorLogin(usuario.getLogin())
                    .ifPresent(u -> {
                        throw new RecursoJaExisteException("Login já cadastrado no sistema");
                    });
        }

        usuario.setCpf(usuarioExistente.getCpf());
        usuario.setSenhaHash(usuarioExistente.getSenhaHash());
        usuario.setAtivo(usuarioExistente.isAtivo());

        usuario.setDataUltimaAlteracao(LocalDateTime.now());

        return usuarioRepositoryOutputPort.salvar(usuario);
    }
}