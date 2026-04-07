package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final BuscarUsuarioInputPort buscarUsuarioInputPort;

    public UsuarioController(BuscarUsuarioInputPort buscarUsuarioInputPort) {
        this.buscarUsuarioInputPort = buscarUsuarioInputPort;
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarPorNome(@RequestParam String nome) {
        var usuario = buscarUsuarioInputPort.buscarPorNome(nome);
        return ResponseEntity.ok(usuario);
    }
}
