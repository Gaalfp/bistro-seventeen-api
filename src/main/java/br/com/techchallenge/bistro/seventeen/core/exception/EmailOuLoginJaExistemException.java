package br.com.techchallenge.bistro.seventeen.core.exception;

public class EmailOuLoginJaExistemException extends RuntimeException {

    public EmailOuLoginJaExistemException(String message) {
        super(message);
    }

    public EmailOuLoginJaExistemException(String message, Throwable cause) {
        super(message, cause);
    }
}

