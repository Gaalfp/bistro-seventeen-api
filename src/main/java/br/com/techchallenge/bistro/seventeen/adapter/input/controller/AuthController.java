package br.com.techchallenge.bistro.seventeen.adapter.input.controller;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.LoginRequestDTO;
import br.com.techchallenge.bistro.seventeen.port.input.ValidarLoginInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para validação de acesso")
public class AuthController {

    private final ValidarLoginInputPort validarLoginInputPort;

    @Operation(summary = "Realizar login", description = "Valida as credenciais do usuário (login e senha)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        String resultado = validarLoginInputPort.executar(
                loginRequestDTO.login(),
                loginRequestDTO.senha()
        );
        return ResponseEntity.ok(resultado);
    }
}