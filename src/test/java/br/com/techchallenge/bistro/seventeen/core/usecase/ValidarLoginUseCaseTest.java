package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.adapter.exception.CredenciaisInvalidasException;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidarLoginUseCaseTest {

    @Mock
    private UsuarioRepositoryOutputPort consultarUsuarioPort;

    @Mock
    private PasswordEncoderOutputPort passwordEncoderPort;

    @InjectMocks
    private ValidarLoginUseCase useCase;

    @Test
    @DisplayName("Deve retornar mensagem de sucesso quando login e senha estiverem corretos e usuário ativo")
    void deveLogarComSucesso() {
        var usuario = new Usuario();
        usuario.setLogin("admin.master");
        usuario.setSenhaHash("hash_bcrypted");
        usuario.setAtivo(true);

        when(consultarUsuarioPort.buscarPorLogin("admin.master"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoderPort.matches("senha123", "hash_bcrypted"))
                .thenReturn(true);

        String resultado = useCase.executar("admin.master", "senha123");

        assertThat(resultado).isEqualTo("Login realizado com sucesso!");
    }

    @Test
    @DisplayName("Deve lançar CredenciaisInvalidasException quando o usuário for encontrado mas estiver inativo")
    void deveFalharUsuarioInativo() {
        var usuario = new Usuario();
        usuario.setLogin("admin.master");
        usuario.setAtivo(false);

        when(consultarUsuarioPort.buscarPorLogin("admin.master"))
                .thenReturn(Optional.of(usuario));

        assertThatThrownBy(() -> useCase.executar("admin.master", "senha123"))
                .isInstanceOf(CredenciaisInvalidasException.class)
                .hasMessage("Credenciais inválidas");

        verify(passwordEncoderPort, never()).matches(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve lançar CredenciaisInvalidasException quando a senha estiver incorreta")
    void deveFalharSenhaIncorreta() {
        var usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setSenhaHash("hash_correto");

        when(consultarUsuarioPort.buscarPorLogin("admin.master"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoderPort.matches("senha_errada", "hash_correto"))
                .thenReturn(false);

        assertThatThrownBy(() -> useCase.executar("admin.master", "senha_errada"))
                .isInstanceOf(CredenciaisInvalidasException.class)
                .hasMessage("Credenciais inválidas");
    }

    @Test
    @DisplayName("Deve lançar CredenciaisInvalidasException quando o login não existir")
    void deveFalharLoginInexistente() {
        when(consultarUsuarioPort.buscarPorLogin("fantasma"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.executar("fantasma", "qualquer_senha"))
                .isInstanceOf(CredenciaisInvalidasException.class)
                .hasMessage("Credenciais inválidas");
    }
}