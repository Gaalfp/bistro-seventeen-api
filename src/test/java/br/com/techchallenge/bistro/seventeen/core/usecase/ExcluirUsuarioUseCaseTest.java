package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.adapter.exception.CredenciaisInvalidasException;
import br.com.techchallenge.bistro.seventeen.adapter.exception.EntidadeNaoEncontradaException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExcluirUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryOutputPort repository;

    @InjectMocks
    private ExcluirUsuarioUseCase useCase;

    @Test
    @DisplayName("Deve inativar usuário com sucesso quando ele existe e está ativo")
    void deveInativarUsuarioComSucesso() {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setAtivo(true);

        when(repository.buscarPorId(id)).thenReturn(Optional.of(usuario));

        useCase.excluir(id);

        assertFalse(usuario.isAtivo(), "O usuário deveria estar inativo após a exclusão lógica");
        verify(repository, times(1)).salvar(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não for encontrado")
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        UUID id = UUID.randomUUID();
        when(repository.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> useCase.excluir(id));
        verify(repository, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar excluir usuário já inativo")
    void deveLancarExcecaoQuandoUsuarioJaInativo() {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setAtivo(false);

        when(repository.buscarPorId(id)).thenReturn(Optional.of(usuario));

        CredenciaisInvalidasException exception = assertThrows(
                CredenciaisInvalidasException.class,
                () -> useCase.excluir(id)
        );

        assertEquals("Usuário já se encontra inativo no sistema.", exception.getMessage());
        verify(repository, never()).salvar(any());
    }
}