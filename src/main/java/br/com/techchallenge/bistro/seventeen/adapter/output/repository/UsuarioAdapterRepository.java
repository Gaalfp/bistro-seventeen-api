package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioAdapterRepository implements UsuarioRepositoryOutputPort, ConsultarUsuarioPorLoginOutputPort {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .map(mapper::toUsuario);
    }
}