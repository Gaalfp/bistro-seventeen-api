package br.com.techchallenge.bistro.seventeen.config;

import br.com.techchallenge.bistro.seventeen.core.usecase.ConsultarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.port.input.ConsultarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ConsultarUsuarioInputPort consultarUsuarioInputPort(UsuarioRepositoryOutputPort  usuarioRepositoryOutputPort) {
        return new ConsultarUsuarioUseCase(usuarioRepositoryOutputPort);
    }
}
