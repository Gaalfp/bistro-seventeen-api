package br.com.techchallenge.bistro.seventeen.core.exception;

public class RecursoJaExisteException extends RuntimeException {

    public RecursoJaExisteException(String message) {
        super(message);
    }

    public RecursoJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}

