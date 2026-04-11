package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByLogin(String login) {
        return usuarioRepository.existsByLogin(login);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity persisted = usuarioRepository.save(entity);
        return paraDomainWithoutSenha(persisted);
    }

    private static UsuarioEntity toEntity(Usuario usuario) {

        UsuarioEntity entityReturn = new UsuarioEntity(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                null, //cpf full padrao
                usuario.getSenhaHash(),
                usuario.getTipo(),
                usuario.getStatus(),
                usuario.getDataUltimaAlteracao(),
                usuario.getAtivo(),
                usuario.getEndereco()
        );

        return entityReturn;
    }

    private static Usuario paraDomainWithoutSenha(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getLogin(),
                null,
                entity.getEndereco(),
                entity.getTipoUsuario(),
                entity.getDataUltimaAlteracao(),
                entity.getStatus(),
                entity.getAtivo()
        );
    }
}