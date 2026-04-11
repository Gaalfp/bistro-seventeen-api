package br.com.techchallenge.bistro.seventeen.adapter.input.mapper;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.AtualizarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.response.UsuarioResponse;
import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-11T14:55:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toUsuario(UsuarioEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        if ( entity.getTipoUsuario() != null ) {
            usuario.tipoUsuario( Enum.valueOf( TipoUsuario.class, entity.getTipoUsuario() ) );
        }
        usuario.id( entity.getId() );
        usuario.nome( entity.getNome() );
        usuario.email( entity.getEmail() );
        usuario.login( entity.getLogin() );
        usuario.cpf( entity.getCpf() );
        usuario.senhaHash( entity.getSenhaHash() );
        usuario.endereco( entity.getEndereco() );
        usuario.dataUltimaAlteracao( entity.getDataUltimaAlteracao() );
        if ( entity.getAtivo() != null ) {
            usuario.ativo( entity.getAtivo() );
        }

        return usuario.build();
    }

    @Override
    public Usuario fromDtoToUsuario(UUID id, AtualizarUsuarioRequest atualizarUsuarioRequest) {
        if ( id == null && atualizarUsuarioRequest == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        if ( atualizarUsuarioRequest != null ) {
            usuario.nome( atualizarUsuarioRequest.nome() );
            usuario.email( atualizarUsuarioRequest.email() );
            usuario.login( atualizarUsuarioRequest.login() );
            usuario.endereco( atualizarUsuarioRequest.endereco() );
        }
        usuario.id( id );

        return usuario.build();
    }

    @Override
    public UsuarioEntity toEntity(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        if ( usuario.getTipoUsuario() != null ) {
            usuarioEntity.setTipoUsuario( usuario.getTipoUsuario().name() );
        }
        usuarioEntity.setId( usuario.getId() );
        usuarioEntity.setNome( usuario.getNome() );
        usuarioEntity.setEmail( usuario.getEmail() );
        usuarioEntity.setEndereco( usuario.getEndereco() );
        usuarioEntity.setLogin( usuario.getLogin() );
        usuarioEntity.setCpf( usuario.getCpf() );
        usuarioEntity.setSenhaHash( usuario.getSenhaHash() );
        usuarioEntity.setDataUltimaAlteracao( usuario.getDataUltimaAlteracao() );
        usuarioEntity.setAtivo( usuario.isAtivo() );

        return usuarioEntity;
    }

    @Override
    public UsuarioResponse toResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        String email = null;
        String login = null;
        String endereco = null;
        TipoUsuario tipoUsuario = null;
        LocalDateTime dataUltimaAlteracao = null;

        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        login = usuario.getLogin();
        endereco = usuario.getEndereco();
        tipoUsuario = usuario.getTipoUsuario();
        dataUltimaAlteracao = usuario.getDataUltimaAlteracao();

        UsuarioResponse usuarioResponse = new UsuarioResponse( id, nome, email, login, endereco, tipoUsuario, dataUltimaAlteracao );

        return usuarioResponse;
    }
}
