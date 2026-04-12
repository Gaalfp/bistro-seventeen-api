package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.exception.RecursoEmConflitoException;
import br.com.techchallenge.bistro.seventeen.core.exception.RecursoJaExisteException;
import br.com.techchallenge.bistro.seventeen.core.exception.UsuarioNaoEncontradoException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryOutputPort usuarioRepositoryOutputPort;

    @InjectMocks
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Test
    @DisplayName("Deve atualizar usuário preservando dados sensíveis e data de alteração")
    void deveAtualizarUsuarioComSucesso() {
        UUID id = UUID.randomUUID();
        Usuario usuarioExistente = Usuario.builder()
                .id(id)
                .email("antigo@email.com")
                .login("login.antigo")
                .cpf("11122233344")
                .senhaHash("hash_antigo")
                .ativo(true)
                .build();

        Usuario usuarioParaAtualizar = Usuario.builder()
                .id(id)
                .nome("Nome Novo")
                .email("novo@email.com")
                .login("login.novo")
                .build();

        when(usuarioRepositoryOutputPort.buscarPorId(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryOutputPort.buscarPorEmail("novo@email.com")).thenReturn(Optional.empty());
        when(usuarioRepositoryOutputPort.buscarPorLogin("login.novo")).thenReturn(Optional.empty());
        when(usuarioRepositoryOutputPort.salvar(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario resultado = atualizarUsuarioUseCase.atualizarUsuario(usuarioParaAtualizar);

        assertNotNull(resultado);
        assertEquals("Nome Novo", resultado.getNome());
        assertEquals("novo@email.com", resultado.getEmail());
        assertEquals("11122233344", resultado.getCpf());
        assertEquals("hash_antigo", resultado.getSenhaHash());
        assertTrue(resultado.isAtivo());
        assertNotNull(resultado.getDataUltimaAlteracao());

        verify(usuarioRepositoryOutputPort, times(1)).salvar(usuarioParaAtualizar);
    }

    @Test
    @DisplayName("Deve lançar UsuarioNaoEncontradoException quando ID não existir no banco")
    void deveLancarExceptionQuandoUsuarioNaoEncontrado() {
        UUID id = UUID.randomUUID();
        Usuario usuario = Usuario.builder().id(id).build();

        when(usuarioRepositoryOutputPort.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> atualizarUsuarioUseCase.atualizarUsuario(usuario));
        verify(usuarioRepositoryOutputPort, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve lançar RecursoEmConflitoException ao tentar atualizar usuário inativo")
    void deveLancarExceptionQuandoUsuarioEstiverInativo() {
        UUID id = UUID.randomUUID();
        Usuario usuarioExistente = Usuario.builder().id(id).ativo(false).build();
        Usuario usuarioParaAtualizar = Usuario.builder().id(id).build();

        when(usuarioRepositoryOutputPort.buscarPorId(id)).thenReturn(Optional.of(usuarioExistente));

        assertThrows(RecursoEmConflitoException.class, () -> atualizarUsuarioUseCase.atualizarUsuario(usuarioParaAtualizar));
        verify(usuarioRepositoryOutputPort, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve lançar RecursoJaExisteException se o novo email já estiver em uso")
    void deveLancarExceptionQuandoEmailJaExistir() {
        UUID id = UUID.randomUUID();
        Usuario usuarioExistente = Usuario.builder().id(id).email("antigo@email.com").ativo(true).build();
        Usuario usuarioParaAtualizar = Usuario.builder().id(id).email("em.uso@email.com").build();

        Usuario outroUsuarioComMesmoEmail = Usuario.builder().id(UUID.randomUUID()).build();

        when(usuarioRepositoryOutputPort.buscarPorId(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryOutputPort.buscarPorEmail("em.uso@email.com")).thenReturn(Optional.of(outroUsuarioComMesmoEmail));

        assertThrows(RecursoJaExisteException.class, () -> atualizarUsuarioUseCase.atualizarUsuario(usuarioParaAtualizar));
        verify(usuarioRepositoryOutputPort, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve lançar RecursoJaExisteException se o novo login já estiver em uso")
    void deveLancarExceptionQuandoLoginJaExistir() {
        UUID id = UUID.randomUUID();
        Usuario usuarioExistente = Usuario.builder().id(id).email("email@teste.com").login("login.antigo").ativo(true).build();
        Usuario usuarioParaAtualizar = Usuario.builder().id(id).email("email@teste.com").login("login.em.uso").build();

        Usuario outroUsuarioComMesmoLogin = Usuario.builder().id(UUID.randomUUID()).build();

        when(usuarioRepositoryOutputPort.buscarPorId(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryOutputPort.buscarPorLogin("login.em.uso")).thenReturn(Optional.of(outroUsuarioComMesmoLogin));

        assertThrows(RecursoJaExisteException.class, () -> atualizarUsuarioUseCase.atualizarUsuario(usuarioParaAtualizar));
        verify(usuarioRepositoryOutputPort, never()).salvar(any());
    }
}