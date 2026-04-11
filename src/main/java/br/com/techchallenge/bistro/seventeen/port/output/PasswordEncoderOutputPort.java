package br.com.techchallenge.bistro.seventeen.port.output;

public interface PasswordEncoderOutputPort {
    boolean matches(String rawPassword, String encodedPassword);
}