package br.com.techchallenge.bistro.seventeen.adapter.input.mapper;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.AtualizarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.response.UsuarioResponse;
import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "tipoUsuario", source = "tipoUsuario")
    Usuario toUsuario(UsuarioEntity entity);

    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "senhaHash", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Usuario fromDtoToUsuario(UUID id, AtualizarUsuarioRequest atualizarUsuarioRequest);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario")
    UsuarioEntity toEntity(Usuario usuario);

    UsuarioResponse toResponse(Usuario usuario);
}