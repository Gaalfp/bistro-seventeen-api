package br.com.techchallenge.bistro.seventeen.adapter.output.security;

import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptAdapter implements PasswordEncoderOutputPort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}