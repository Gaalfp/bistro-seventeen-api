package br.com.techchallenge.bistro.seventeen.config;

import br.com.techchallenge.bistro.seventeen.core.usecase.*;
import br.com.techchallenge.bistro.seventeen.port.input.*;
import br.com.techchallenge.bistro.seventeen.port.output.ConsultarUsuarioPorLoginOutputPort;
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

    @Bean
    public TrocarSenhaInputPort trocarSenhaUseCase(UsuarioRepositoryOutputPort repository, PasswordEncoder passwordEncoder) {
        return new TrocarSenhaUseCase(repository, passwordEncoder);
    }

    @Bean
    public CadastrarUsuarioInputPort cadastrarUsuarioInputPort(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort
            , PasswordEncoder passwordEncoder) {
        return new CadastrarUsuarioUseCase(usuarioRepositoryOutputPort, passwordEncoder);
    }
}
