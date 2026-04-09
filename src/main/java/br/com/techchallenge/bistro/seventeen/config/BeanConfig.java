package br.com.techchallenge.bistro.seventeen.config;

import br.com.techchallenge.bistro.seventeen.core.usecase.BuscarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.AtualizarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.ConsultarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.TrocarSenhaUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.ValidarLoginUseCase;
import br.com.techchallenge.bistro.seventeen.port.input.BuscarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.AtualizarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.ConsultarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.TrocarSenhaInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.ValidarLoginInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.PasswordEncoderOutputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ValidarLoginInputPort validarLoginInputPort(
            UsuarioRepositoryOutputPort usuarioRepositoryOutputPort,
            PasswordEncoderOutputPort passwordEncoderPort) {
        return new ValidarLoginUseCase(usuarioRepositoryOutputPort, passwordEncoderPort);
    }

    @Bean
    public ConsultarUsuarioInputPort consultarUsuarioInputPort(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort) {
        return new ConsultarUsuarioUseCase(usuarioRepositoryOutputPort);
    }

    @Bean
    public AtualizarUsuarioInputPort atualizarUsuarioInputPort(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort) {
        return new AtualizarUsuarioUseCase(usuarioRepositoryOutputPort);
    }

    @Bean
    public BuscarUsuarioInputPort buscarUsuarioUseCase(UsuarioRepositoryOutputPort repository) {
        return new BuscarUsuarioUseCase(repository);
    }

    @Bean
    public TrocarSenhaInputPort trocarSenhaUseCase(UsuarioRepositoryOutputPort repository, PasswordEncoder passwordEncoder) {
        return new TrocarSenhaUseCase(repository, passwordEncoder);
    }
}
