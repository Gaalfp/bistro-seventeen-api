package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidarLoginUseCaseTest {

    @Mock
    private ConsultarUsuarioPorLoginOutputPort consultarUsuarioPort;

    @Mock
    private PasswordEncoderOutputPort passwordEncoderPort;

    @InjectMocks
    private ValidarLoginUseCase useCase;

    @Test
    @DisplayName("Deve retornar mensagem de sucesso quando login e senha estiverem corretos")
    void deveLogarComSucesso() {
        // Arrange
        var usuario = new Usuario();
        usuario.setLogin("admin.master");
        usuario.setSenhaHash("hash_bcrypted");

        when(consultarUsuarioPort.buscarPorLogin("admin.master"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoderPort.matches("senha123", "hash_bcrypted"))
                .thenReturn(true);

        // Act
        String resultado = useCase.executar("admin.master", "senha123");

        // Assert
        assertThat(resultado).isEqualTo("Login realizado com sucesso!");
    }

    @Test
    @DisplayName("Deve lancar excecao quando a senha estiver incorreta")
    void deveFalharSenhaIncorreta() {
        var usuario = new Usuario();
        usuario.setSenhaHash("hash_correto");

        when(consultarUsuarioPort.buscarPorLogin("admin.master"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoderPort.matches("senha_errada", "hash_correto"))
                .thenReturn(false);

        assertThatThrownBy(() -> useCase.executar("admin.master", "senha_errada"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Credenciais inválidas");
    }
}