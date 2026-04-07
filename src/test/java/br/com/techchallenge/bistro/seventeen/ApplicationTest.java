package br.com.techchallenge.bistro.seventeen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTests {

    @Test
    @DisplayName("Deve carregar o contexto do Spring sem erros")
    void contextLoads() {
    }
}