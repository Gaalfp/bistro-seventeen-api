package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.adapter.input.mapper.UsuarioMapper;
import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioAdapterRepositoryTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioMapper mapper;

    @InjectMocks
    private UsuarioAdapterRepository adapter;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail("teste@bistro.com");

        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(id);
        usuarioEntity.setEmail("teste@bistro.com");
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        when(mapper.toEntity(any(Usuario.class))).thenReturn(usuarioEntity);
        when(repository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        when(mapper.toUsuario(any(UsuarioEntity.class))).thenReturn(usuario);

        Usuario resultado = adapter.salvar(usuario);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(repository).save(usuarioEntity);
    }

    @Test
    void deveBuscarPorEmailComSucesso() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(usuarioEntity));
        when(mapper.toUsuario(any(UsuarioEntity.class))).thenReturn(usuario);

        Optional<Usuario> resultado = adapter.buscarPorEmail("teste@bistro.com");

        assertTrue(resultado.isPresent());
        verify(repository).findByEmail("teste@bistro.com");
    }

    @Test
    void deveRetornarVazioQuandoBuscarPorEmailNaoExistente() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        Optional<Usuario> resultado = adapter.buscarPorEmail("naoexiste@bistro.com");

        assertTrue(resultado.isEmpty());
        verify(repository).findByEmail("naoexiste@bistro.com");
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(usuarioEntity));
        when(mapper.toUsuario(any(UsuarioEntity.class))).thenReturn(usuario);

        Optional<Usuario> resultado = adapter.buscarPorId(id);

        assertTrue(resultado.isPresent());
        verify(repository).findById(id);
    }

    @Test
    void deveListarTodosComSucesso() {
        when(repository.findAll()).thenReturn(List.of(usuarioEntity));
        when(mapper.toUsuario(any(UsuarioEntity.class))).thenReturn(usuario);

        List<Usuario> resultado = adapter.listarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(repository).findAll();
    }
}