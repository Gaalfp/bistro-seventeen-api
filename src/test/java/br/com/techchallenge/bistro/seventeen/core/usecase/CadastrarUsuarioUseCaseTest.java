package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.exception.RecursoJaExisteException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryOutputPort repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CadastrarUsuarioUseCase useCase;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setEmail("teste@bistro.com");
        usuario.setLogin("teste.login");
        usuario.setSenhaHash("senha123");
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        when(repository.buscarPorEmail(anyString())).thenReturn(Optional.empty());
        when(repository.buscarPorLogin(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("hashSeguro");
        when(repository.salvar(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = useCase.cadastrar(usuario);

        assertNotNull(resultado);
        verify(passwordEncoder).encode("senha123");
        verify(repository).salvar(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        when(repository.buscarPorEmail(anyString())).thenReturn(Optional.of(new Usuario()));

        assertThrows(RecursoJaExisteException.class, () -> useCase.cadastrar(usuario));
        verify(repository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoLoginJaExiste() {
        when(repository.buscarPorEmail(anyString())).thenReturn(Optional.empty());
        when(repository.buscarPorLogin(anyString())).thenReturn(Optional.of(new Usuario()));

        assertThrows(RecursoJaExisteException.class, () -> useCase.cadastrar(usuario));
        verify(repository, never()).salvar(any());
    }
}