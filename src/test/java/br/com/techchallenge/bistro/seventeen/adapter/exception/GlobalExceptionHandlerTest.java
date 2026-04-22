package br.com.techchallenge.bistro.seventeen.adapter.exception;

import br.com.techchallenge.bistro.seventeen.core.exception.RecursoJaExisteException;
import br.com.techchallenge.bistro.seventeen.core.exception.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void deveTratarMethodArgumentNotValid() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "email", "E-mail inválido");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ProblemDetail response = exceptionHandler.handleMethodArgumentNotValid(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Falha na validação dos dados fornecidos", response.getDetail());

        Map<String, String> errors = (Map<String, String>) response.getProperties().get("errors");
        assertNotNull(errors);
        assertEquals("E-mail inválido", errors.get("email"));
    }

    @Test
    void deveTratarUsuarioNaoEncontrado() {
        UsuarioNaoEncontradoException ex = new UsuarioNaoEncontradoException("Usuário não encontrado");

        ProblemDetail response = exceptionHandler.handleUsuarioNaoEncontrado(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Usuário não encontrado", response.getDetail());
    }

    @Test
    void deveTratarEmailOuLoginJaExistem() {
        RecursoJaExisteException ex = new RecursoJaExisteException("E-mail já cadastrado");

        ProblemDetail response = exceptionHandler.handleEmailOuLoginJaExistem(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        assertEquals("E-mail já cadastrado", response.getDetail());
    }

    @Test
    void deveTratarRuntimeException() {
        RuntimeException ex = new RuntimeException("Erro inesperado no servidor");

        ProblemDetail response = exceptionHandler.handleRuntimeException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("Erro inesperado no servidor", response.getDetail());
    }

    @Test
    void deveTratarEntidadeNaoEncontradaException() {
        EntidadeNaoEncontradaException ex = new EntidadeNaoEncontradaException("Entidade não encontrada");

        ProblemDetail response = exceptionHandler.handleEntidadeNaoEncontradaException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Entidade não encontrada", response.getDetail());
    }

    @Test
    void deveTratarSenhaAtualIncorretaException() {
        SenhaAtualIncorretaException ex = new SenhaAtualIncorretaException("Senha atual incorreta");

        ProblemDetail response = exceptionHandler.handleSenhaAtualIncorretaException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Senha atual incorreta", response.getDetail());
    }

    @Test
    void deveTratarNoHandlerFoundException() {
        HttpHeaders headers = new HttpHeaders();
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/api/v1/rota-perdida", headers);

        ProblemDetail response = exceptionHandler.handleNoHandlerFoundException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Recurso não encontrado", response.getDetail());
        assertEquals("/api/v1/rota-perdida", response.getProperties().get("path"));
    }

    @Test
    void deveTratarCredenciaisInvalidas() {
        CredenciaisInvalidasException ex = new CredenciaisInvalidasException("Login ou senha inválidos");

        ProblemDetail response = exceptionHandler.handleCredenciaisInvalidas(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals("Login ou senha inválidos", response.getDetail());
    }
}