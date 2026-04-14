package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.TrocarSenhaRequest;
import br.com.techchallenge.bistro.seventeen.adapter.exception.SenhaAtualIncorretaException;
import br.com.techchallenge.bistro.seventeen.adapter.exception.EntidadeNaoEncontradaException;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrocarSenhaUseCaseTest {

    @Mock
    private UsuarioRepositoryOutputPort repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TrocarSenhaUseCase useCase;

    private UUID usuarioId;
    private TrocarSenhaRequest request;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        request = new TrocarSenhaRequest("senhaAntiga", "novaSenhaForte");
        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setSenhaHash("hashNoBancoDeDados");
    }

    @Test
    void deveTrocarSenhaComSucesso() {
        when(repository.buscarPorId(usuarioId)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senhaAntiga", "hashNoBancoDeDados")).thenReturn(true);
        when(passwordEncoder.encode("novaSenhaForte")).thenReturn("novoHashSeguro");

        useCase.trocarSenha(usuarioId, request);

        verify(repository).salvar(usuario);
        verify(passwordEncoder).encode("novaSenhaForte");
    }

    @Test
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {
        when(repository.buscarPorId(usuarioId)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senhaAntiga", "hashNoBancoDeDados")).thenReturn(false);

        assertThrows(SenhaAtualIncorretaException.class, () -> useCase.trocarSenha(usuarioId, request));
        verify(repository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(repository.buscarPorId(usuarioId)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> useCase.trocarSenha(usuarioId, request));
    }
}