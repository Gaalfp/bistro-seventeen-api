package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.AtualizarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.TrocarSenhaRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.CadastrarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.response.CadastrarUsuarioResponse;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.response.UsuarioResponse;
import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.techchallenge.bistro.seventeen.adapter.input.controller.RouteConstants.BASE_PATH;
import static br.com.techchallenge.bistro.seventeen.adapter.input.controller.RouteConstants.USUARIOS;

@RestController
@RequestMapping(BASE_PATH + USUARIOS)
@Tag(name = "Usuários", description = "Endpoints para operações de gerenciamento de usuários do sistema")
public class UsuarioController {

    private final AtualizarUsuarioInputPort atualizarUsuarioInputPort;
    private final ExcluirUsuarioInputPort excluirUsuarioInputPort;
    private final BuscarUsuarioInputPort buscarUsuarioInputPort;
    private final TrocarSenhaInputPort trocarSenhaInputPort;
    private final CadastrarUsuarioInputPort cadastrarUsuarioInputPort;
    private final UsuarioMapper mapper;

    public UsuarioController(AtualizarUsuarioInputPort atualizarUsuarioInputPort,
                             ExcluirUsuarioInputPort excluirUsuarioInputPort,
                             BuscarUsuarioInputPort buscarUsuarioInputPort,
                             TrocarSenhaInputPort trocarSenhaInputPort,
                             CadastrarUsuarioInputPort cadastrarUsuarioInputPort,
                             UsuarioMapper mapper) {
        this.atualizarUsuarioInputPort = atualizarUsuarioInputPort;
        this.excluirUsuarioInputPort = excluirUsuarioInputPort;
        this.buscarUsuarioInputPort = buscarUsuarioInputPort;
        this.trocarSenhaInputPort = trocarSenhaInputPort;
        this.cadastrarUsuarioInputPort = cadastrarUsuarioInputPort;
        this.mapper = mapper;
    }

    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário no sistema com as credenciais fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = CadastrarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Conflito de dados (E-mail ou Login já existentes)",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<CadastrarUsuarioResponse> cadastrar(@Valid @RequestBody CadastrarUsuarioRequest request) {
        Usuario usuario = mapper.fromCadastrarRequestToUsuario(request);
        Usuario criado = cadastrarUsuarioInputPort.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(CadastrarUsuarioResponse.from(criado));
    }

    @Operation(summary = "Listar ou buscar usuários", description = "Retorna uma lista de usuários. Pode ser filtrada pelo termo 'nome'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class))))
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios(@RequestParam(required = false) String nome) {
        List<Usuario> usuarios;

        if (nome != null && !nome.isBlank()) {
            usuarios = buscarUsuarioInputPort.buscarPorNome(nome);
        } else {
            usuarios = buscarUsuarioInputPort.buscarTodos();
        }

        var response = usuarios.stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados cadastrais de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable UUID id,
                                                            @Valid @RequestBody AtualizarUsuarioRequest atualizarUsuarioRequest){
        var usuario = mapper.fromDtoToUsuario(id, atualizarUsuarioRequest);
        var usuarioAtualizado = atualizarUsuarioInputPort.atualizarUsuario(usuario);
        var response = mapper.toResponse(usuarioAtualizado);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir usuário", description = "Realiza a exclusão lógica do usuário no sistema (inativação).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        excluirUsuarioInputPort.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Trocar senha", description = "Permite que o usuário altere sua senha informando a senha atual para validação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PatchMapping(value = "/{id}/senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable UUID id, @Valid @RequestBody TrocarSenhaRequest dto) {
        trocarSenhaInputPort.trocarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }
}