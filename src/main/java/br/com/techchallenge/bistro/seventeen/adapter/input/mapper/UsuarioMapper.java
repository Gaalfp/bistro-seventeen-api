package br.com.techchallenge.bistro.seventeen.adapter.input.mapper;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.AtualizarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.CadastrarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.response.UsuarioResponse;
import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "senhaHash", source = "senha")
    @Mapping(target = "tipoUsuario", source = "role")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    Usuario fromCadastrarRequestToUsuario(CadastrarUsuarioRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "login", source = "request.login")
    @Mapping(target = "endereco", source = "request.endereco")
    @Mapping(target = "tipoUsuario", source = "request.tipoUsuario")
    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "senhaHash", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Usuario fromDtoToUsuario(UUID id, AtualizarUsuarioRequest request);

    Usuario toUsuario(UsuarioEntity entity);

    UsuarioEntity toEntity(Usuario usuario);

    UsuarioResponse toResponse(Usuario usuario);
}