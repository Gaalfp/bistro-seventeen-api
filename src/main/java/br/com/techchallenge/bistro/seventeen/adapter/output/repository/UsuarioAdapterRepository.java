package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id).map(mapper::toUsuario);
    }

    @Override
    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .map(mapper::toUsuario);
    }

    @Override
    public void salvar(Usuario usuario) {
        usuarioRepository.save(mapper.toEntity(usuario));
    }

    @Override
    public Optional<Usuario> buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .map(mapper::toUsuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .map(mapper::toUsuario);
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
        var entity = mapper.toEntity(usuario);
        var usuarioSalvo = usuarioRepository.save(entity);
        return mapper.toUsuario(usuarioSalvo);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(mapper::toUsuario);
    }
}