package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.AtualizarUsuarioDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.TrocarSenhaRequestDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioResponseDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.AtualizarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.TrocarSenhaInputPort;
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
    private final BuscarUsuarioInputPort buscarUsuarioInputPort;
    private final TrocarSenhaInputPort trocarSenhaInputPort;
    private final UsuarioMapper mapper;

    public UsuarioController(AtualizarUsuarioInputPort atualizarUsuarioInputPort, BuscarUsuarioInputPort buscarUsuarioInputPort, TrocarSenhaInputPort trocarSenhaInputPort, UsuarioMapper mapper) {
        this.atualizarUsuarioInputPort = atualizarUsuarioInputPort;
        this.buscarUsuarioInputPort = buscarUsuarioInputPort;
        this.trocarSenhaInputPort = trocarSenhaInputPort;
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

    @GetMapping
    public ResponseEntity<Usuario> buscarPorNome(@RequestParam String nome) {
        var usuario = buscarUsuarioInputPort.buscarPorNome(nome);
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("{id}/senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable UUID id, @RequestBody TrocarSenhaRequestDTO dto) {
        trocarSenhaInputPort.trocarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }
}
