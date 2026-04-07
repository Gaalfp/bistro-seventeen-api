package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.exception.EmailOuLoginJaExistemException;
import br.com.techchallenge.bistro.seventeen.core.exception.UsuarioNaoEncontradoException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.AtualizarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;

public class AtualizarUsuarioUseCase implements AtualizarUsuarioInputPort {

    private final UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;

    public AtualizarUsuarioUseCase(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort) {
        this.usuarioRepositoryOutputPort = usuarioRepositoryOutputPort;
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        var usuarioExistente = usuarioRepositoryOutputPort.buscarPorId(usuario.getId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(
                        "Usuário com ID " + usuario.getId() + " não encontrado"
                ));

        if (!usuarioExistente.getEmail().equals(usuario.getEmail())) {
            usuarioRepositoryOutputPort.buscarPorEmail(usuario.getEmail())
                    .ifPresent(u -> {
                        throw new EmailOuLoginJaExistemException("Email já cadastrado no sistema");
                    });
        }

        if (!usuarioExistente.getLogin().equals(usuario.getLogin())) {
            usuarioRepositoryOutputPort.buscarPorLogin(usuario.getLogin())
                    .ifPresent(u -> {
                        throw new EmailOuLoginJaExistemException("Login já cadastrado no sistema");
                    });
        }
        usuario.setSenhaHash(usuarioExistente.getSenhaHash());
        return usuarioRepositoryOutputPort.salvar(usuario);
    }
}
