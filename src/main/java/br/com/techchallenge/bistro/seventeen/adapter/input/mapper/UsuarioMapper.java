package br.com.techchallenge.bistro.seventeen.adapter.input.mapper;

import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioEntity entity);

    UsuarioEntity toEntity(Usuario usuario);
}