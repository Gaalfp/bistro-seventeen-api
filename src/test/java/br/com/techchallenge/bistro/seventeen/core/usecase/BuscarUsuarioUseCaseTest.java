package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryOutputPort repository;

    @InjectMocks
    private BuscarUsuarioUseCase useCase;

    @Test
    void deveBuscarTodosOsUsuarios() {
        List<Usuario> utilizadores = List.of(new Usuario(), new Usuario());
        when(repository.listarTodos()).thenReturn(utilizadores);

        List<Usuario> resultado = useCase.buscarTodos();

        assertEquals(2, resultado.size());
        verify(repository).listarTodos();
    }

    @Test
    void deveBuscarUsuariosPorNomeOuTermo() {
        String termoDeBusca = "João";
        List<Usuario> utilizadores = List.of(new Usuario());
        when(repository.buscarPorNome(termoDeBusca)).thenReturn(utilizadores);

        List<Usuario> resultado = useCase.buscarPorNome(termoDeBusca);

        assertEquals(1, resultado.size());
        verify(repository).buscarPorNome(termoDeBusca);
    }
}