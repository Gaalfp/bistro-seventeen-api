package br.com.techchallenge.bistro.seventeen.adapter.input.controller;


import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioCriadoResponse;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioCriarRequest;
import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.CadastrarUsuarioInputPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final CadastrarUsuarioInputPort cadastrarUsuarioInputPort;

    public UsuarioController(CadastrarUsuarioInputPort cadastrarUsuarioInputPort) {
        this.cadastrarUsuarioInputPort = cadastrarUsuarioInputPort;
    }

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
