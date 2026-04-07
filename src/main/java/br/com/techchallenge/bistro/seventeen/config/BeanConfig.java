package br.com.techchallenge.bistro.seventeen.config;

import br.com.techchallenge.bistro.seventeen.core.usecase.BuscarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.ConsultarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.ValidarLoginUseCase;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.ConsultarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.ValidarLoginInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ValidarLoginInputPort validarLoginInputPort(
            ConsultarUsuarioPorLoginOutputPort consultarUsuarioPort,
            PasswordEncoderOutputPort passwordEncoderPort) {
        return new ValidarLoginUseCase(consultarUsuarioPort, passwordEncoderPort);
    }

    @Bean
    public ConsultarUsuarioInputPort consultarUsuarioInputPort(UsuarioRepositoryOutputPort  usuarioRepositoryOutputPort) {
        return new ConsultarUsuarioUseCase(usuarioRepositoryOutputPort);
    }

    @Bean
    public BuscarUsuarioInputPort buscarUsuarioUseCase(UsuarioRepositoryOutputPort repository) {
        return new BuscarUsuarioUseCase(repository);
    }
}
