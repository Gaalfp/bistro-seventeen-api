package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.TrocarSenhaRequestDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioCriadoResponse;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioCriarRequest;
import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.CadastrarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.TrocarSenhaInputPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final BuscarUsuarioInputPort buscarUsuarioInputPort;
    private final TrocarSenhaInputPort trocarSenhaInputPort;

    private final CadastrarUsuarioInputPort cadastrarUsuarioInputPort;

    public UsuarioController(BuscarUsuarioInputPort buscarUsuarioInputPort, TrocarSenhaInputPort trocarSenhaInputPort,
                             CadastrarUsuarioInputPort cadastrarUsuarioInputPort) {
        this.buscarUsuarioInputPort = buscarUsuarioInputPort;
        this.trocarSenhaInputPort = trocarSenhaInputPort;
        this.cadastrarUsuarioInputPort = cadastrarUsuarioInputPort;
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarPorNome(@RequestParam String nome) {
        var usuario = buscarUsuarioInputPort.buscarPorNome(nome);
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable UUID id, @RequestBody TrocarSenhaRequestDTO dto) {
        trocarSenhaInputPort.trocarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }

    /*Criação de Usuario - acesso app*/
    @PostMapping
    public ResponseEntity<UsuarioCriadoResponse> cadastrar
            (@Valid @RequestBody UsuarioCriarRequest request) {

        Usuario usuario = new Usuario(
                request.nome(),
                request.email(),
                request.login(),
                request.senha(),
                request.endereco(),
                TipoUsuario.valueOf(request.role())

        );

        Usuario criado = cadastrarUsuarioInputPort.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioCriadoResponse.from(criado));
    }
}
