package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.AtualizarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.CadastrarUsuarioRequest;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.response.UsuarioResponse;
import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.core.model.TipoUsuario;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AtualizarUsuarioInputPort atualizarUsuarioInputPort;
    @MockitoBean
    private ExcluirUsuarioInputPort excluirUsuarioInputPort;
    @MockitoBean
    private BuscarUsuarioInputPort buscarUsuarioInputPort;
    @MockitoBean
    private TrocarSenhaInputPort trocarSenhaInputPort;
    @MockitoBean
    private CadastrarUsuarioInputPort cadastrarUsuarioInputPort;
    @MockitoBean
    private UsuarioMapper mapper;

    private static final String BASE_URL = "/api/v1/usuarios";

    @Test
    @DisplayName("Deve retornar 201 Created ao cadastrar usuário com dados válidos")
    void deveCadastrarUsuarioComSucesso() throws Exception {
        CadastrarUsuarioRequest request = new CadastrarUsuarioRequest(
                "Carlos", "32051183082", "carlos@email.com", "carlos.cli",
                "Senha123!", "Rua X", "CLIENTE"
        );

        Usuario usuarioMapeado = Usuario.builder()
                .nome(request.nome())
                .login(request.login())
                .email(request.email())
                .build();

        Usuario usuarioSalvo = Usuario.builder()
                .id(UUID.randomUUID())
                .nome(request.nome())
                .login(request.login())
                .email(request.email())
                .tipoUsuario(TipoUsuario.CLIENTE)
                .build();

        when(mapper.fromCadastrarRequestToUsuario(any(CadastrarUsuarioRequest.class))).thenReturn(usuarioMapeado);
        when(cadastrarUsuarioInputPort.cadastrar(any(Usuario.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Carlos"))
                .andExpect(jsonPath("$.login").value("carlos.cli"))
                .andExpect(jsonPath("$.role").value("CLIENTE"));

        verify(cadastrarUsuarioInputPort, times(1)).cadastrar(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request ao tentar cadastrar usuário sem CPF e Email")
    void deveRetornarErroAoCadastrarComDadosInvalidos() throws Exception {
        CadastrarUsuarioRequest requestInvalido = new CadastrarUsuarioRequest(
                "Carlos", "", "email-errado", "carlos.cli",
                "Senha123!", "Rua X", "CLIENTE"
        );

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").exists());

        verify(cadastrarUsuarioInputPort, never()).cadastrar(any());
    }

    @Test
    @DisplayName("Deve retornar 200 OK ao atualizar usuário existente")
    void deveAtualizarUsuarioComSucesso() throws Exception {
        UUID id = UUID.randomUUID();
        AtualizarUsuarioRequest request = new AtualizarUsuarioRequest(
                "Carlos Atualizado", "carlos@email.com", "carlos.cli", "Rua Nova", TipoUsuario.CLIENTE
        );

        Usuario usuarioMapeado = Usuario.builder().id(id).nome("Carlos Atualizado").build();
        UsuarioResponse responseDTO = new UsuarioResponse(id, "Carlos Atualizado", "carlos@email.com", "carlos.cli", "Rua Nova", TipoUsuario.CLIENTE, null);

        when(mapper.fromDtoToUsuario(eq(id), any(AtualizarUsuarioRequest.class))).thenReturn(usuarioMapeado);
        when(atualizarUsuarioInputPort.atualizarUsuario(usuarioMapeado)).thenReturn(usuarioMapeado);
        when(mapper.toResponse(usuarioMapeado)).thenReturn(responseDTO);

        mockMvc.perform(put(BASE_URL + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Carlos Atualizado"));
    }

    @Test
    @DisplayName("Deve retornar 204 No Content ao excluir usuário")
    void deveExcluirUsuarioComSucesso() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete(BASE_URL + "/{id}", id))
                .andExpect(status().isNoContent());

        verify(excluirUsuarioInputPort, times(1)).excluir(id);
    }
}