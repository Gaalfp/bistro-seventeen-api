package br.com.techchallenge.bistro.seventeen.adapter.input.mapper;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.AtualizarUsuarioDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioResponseDTO;
import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "senhaHash", ignore = true)
    @Mapping(target = "dataUltimaAlteracao", ignore = true)
    Usuario fromDtoToUsuario(UUID id, AtualizarUsuarioDTO dto);

    @Mapping(target = "tipoUsuario", source = "tipo")
    UsuarioEntity toEntity(Usuario usuario);

    UsuarioResponseDTO toResponseDto(Usuario usuario);
}