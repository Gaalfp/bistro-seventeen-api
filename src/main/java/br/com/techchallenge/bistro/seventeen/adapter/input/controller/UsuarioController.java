package br.com.techchallenge.bistro.seventeen.adapter.input.controller;


import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.AtualizarUsuarioDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioResponseDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.port.input.AtualizarUsuarioInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.techchallenge.bistro.seventeen.adapter.input.controller.RouteConstants.BASE_PATH;
import static br.com.techchallenge.bistro.seventeen.adapter.input.controller.RouteConstants.USUARIOS;

@RestController
@RequestMapping(BASE_PATH + USUARIOS)
public class UsuarioController {

    private final AtualizarUsuarioInputPort atualizarUsuarioInputPort;
    private final UsuarioMapper mapper;


    public UsuarioController(AtualizarUsuarioInputPort atualizarUsuarioInputPort, UsuarioMapper mapper) {
        this.atualizarUsuarioInputPort = atualizarUsuarioInputPort;
        this.mapper = mapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable UUID id,
                                                               @Valid @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO){
        var usuario = mapper.fromDtoToUsuario(id, atualizarUsuarioDTO);
        var usuarioAtualizado = atualizarUsuarioInputPort.atualizarUsuario(usuario);
        var response = mapper.toResponseDto(usuarioAtualizado);
        return ResponseEntity.ok(response);
    }
}
