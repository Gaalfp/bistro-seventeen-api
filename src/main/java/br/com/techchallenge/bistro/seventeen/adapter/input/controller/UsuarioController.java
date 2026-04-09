package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.AtualizarUsuarioDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.TrocarSenhaRequestDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.UsuarioResponseDTO;
import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.AtualizarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.ExcluirUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.TrocarSenhaInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final UsuarioMapper mapper;

    public UsuarioController(AtualizarUsuarioInputPort atualizarUsuarioInputPort, ExcluirUsuarioInputPort excluirUsuarioInputPort, BuscarUsuarioInputPort buscarUsuarioInputPort, TrocarSenhaInputPort trocarSenhaInputPort, UsuarioMapper mapper) {
        this.atualizarUsuarioInputPort = atualizarUsuarioInputPort;
        this.excluirUsuarioInputPort = excluirUsuarioInputPort;
        this.buscarUsuarioInputPort = buscarUsuarioInputPort;
        this.trocarSenhaInputPort = trocarSenhaInputPort;
        this.mapper = mapper;
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados cadastrais de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Conflito de dados (E-mail ou Login já existentes)",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable UUID id,
                                                               @Valid @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO){
        var usuario = mapper.fromDtoToUsuario(id, atualizarUsuarioDTO);
        var usuarioAtualizado = atualizarUsuarioInputPort.atualizarUsuario(usuario);
        var response = mapper.toResponseDto(usuarioAtualizado);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir usuário", description = "Realiza a exclusão lógica do usuário no sistema (inativação).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário inativado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Operação não permitida (Usuário já inativo)",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        excluirUsuarioInputPort.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuário por nome", description = "Retorna os detalhes de um usuário buscando pelo seu nome exato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> buscarPorNome(@RequestParam String nome) {
        var usuario = buscarUsuarioInputPort.buscarPorNome(nome);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Trocar senha", description = "Permite que o usuário altere sua senha informando a senha atual para validação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos (ex: senha atual incorreta)",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PatchMapping(value = "/{id}/senha", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> trocarSenha(@PathVariable UUID id, @Valid @RequestBody TrocarSenhaRequestDTO dto) {
        trocarSenhaInputPort.trocarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }
}