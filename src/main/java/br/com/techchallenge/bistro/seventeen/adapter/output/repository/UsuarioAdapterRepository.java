package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioAdapterRepository implements UsuarioRepositoryOutputPort {

    private final UsariorRepository usariorRepository;

    @Override
    public boolean existsByEmail(String email) {
        return usariorRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByLogin(String login) {
        return usariorRepository.existsByLogin(login);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity persisted = usariorRepository.save(entity);
        return paraDomainWithoutSenha(persisted);
    }

    private static UsuarioEntity toEntity(Usuario usuario) {

        UsuarioEntity entityReturn = new UsuarioEntity(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            null, //cpf full padrao
            usuario.getSenha(),
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
