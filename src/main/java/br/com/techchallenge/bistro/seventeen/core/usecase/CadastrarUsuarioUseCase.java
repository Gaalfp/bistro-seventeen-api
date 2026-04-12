package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.exception.RecursoJaExisteException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.CadastrarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CadastrarUsuarioUseCase implements CadastrarUsuarioInputPort {

    private final UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;
    private final PasswordEncoder passwordEncoder;

    public CadastrarUsuarioUseCase(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort,
                                   PasswordEncoder passwordEncoder) {
        this.usuarioRepositoryOutputPort = usuarioRepositoryOutputPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepositoryOutputPort.buscarPorEmail(usuario.getEmail()).isPresent()) {
            throw new RecursoJaExisteException("E-mail já cadastrado.");
        }
        if (usuarioRepositoryOutputPort.buscarPorLogin(usuario.getLogin()).isPresent()) {
            throw new RecursoJaExisteException("Login já cadastrado.");
        }

        usuario.setSenhaHash(passwordEncoder.encode(usuario.getSenhaHash()));
        usuario.setAtivo(true);

        return usuarioRepositoryOutputPort.salvar(usuario);
    }
}