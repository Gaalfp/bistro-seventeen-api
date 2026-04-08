package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.TrocarSenhaRequestDTO;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.TrocarSenhaInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final BuscarUsuarioInputPort buscarUsuarioInputPort;
    private final TrocarSenhaInputPort trocarSenhaInputPort;

    public UsuarioController(BuscarUsuarioInputPort buscarUsuarioInputPort, TrocarSenhaInputPort trocarSenhaInputPort) {
        this.buscarUsuarioInputPort = buscarUsuarioInputPort;
        this.trocarSenhaInputPort = trocarSenhaInputPort;
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
