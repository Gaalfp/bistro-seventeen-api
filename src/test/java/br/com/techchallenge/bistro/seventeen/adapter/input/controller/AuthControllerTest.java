package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.port.input.ValidarLoginInputPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ValidarLoginInputPort loginPort;

    @Test
    void deveRetornar200AoFazerLogin() throws Exception {
        String json = """
                {
                    "login": "admin.master",
                    "senha": "123"
                }
                """;

        when(loginPort.executar("admin.master", "123"))
                .thenReturn("Login realizado com sucesso!");

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Login realizado com sucesso!"));
    }
}