package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.exception.RecursoEmConflitoException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.CadastrarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CadastrarUsuarioUseCase implements CadastrarUsuarioInputPort {

    private static final String STATUS_ATIVO = "ATIVO";

    private final UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;
    private final PasswordEncoder passwordEncoder;

    public CadastrarUsuarioUseCase(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort,
                                   PasswordEncoder passwordEncoder) {

        this.usuarioRepositoryOutputPort = usuarioRepositoryOutputPort;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepositoryOutputPort.existsByEmail(usuario.getEmail())) {
            throw new RecursoEmConflitoException("E-mail já cadastrado.");
        }
        if (usuarioRepositoryOutputPort.existsByLogin(usuario.getLogin())) {
            throw new RecursoEmConflitoException("Login já cadastrado.");
        }

        // Garantir que o campo updated_at seja preenchido automaticamente com a data/hora atual no momento da criação.
        LocalDateTime agora = LocalDateTime.now();
        Usuario paraPersistir = new Usuario(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                passwordEncoder.encode(usuario.getSenha()),
                usuario.getEndereco(),
                usuario.getTipo(),
                agora,
                STATUS_ATIVO,
                Boolean.TRUE
        );

        return usuarioRepositoryOutputPort.save(paraPersistir);
    }

}
