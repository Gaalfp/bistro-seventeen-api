package br.com.techchallenge.bistro.seventeen.adapter.output.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class BCryptAdapterTest {

    private final BCryptAdapter adapter = new BCryptAdapter();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void deveValidarHashCorretamente() {
        String senhaPura = "minha_senha";
        String hash = encoder.encode(senhaPura);

        boolean matches = adapter.matches(senhaPura, hash);

        assertThat(matches).isTrue();
    }
}