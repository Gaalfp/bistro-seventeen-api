package br.com.techchallenge.bistro.seventeen.config;

import br.com.techchallenge.bistro.seventeen.core.usecase.CadastrarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.core.usecase.ConsultarUsuarioUseCase;
import br.com.techchallenge.bistro.seventeen.port.input.CadastrarUsuarioInputPort;
import br.com.techchallenge.bistro.seventeen.port.input.ConsultarUsuarioInputPort;
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
    public ConsultarUsuarioInputPort consultarUsuarioInputPort(UsuarioRepositoryOutputPort  usuarioRepositoryOutputPort) {
        return new ConsultarUsuarioUseCase(usuarioRepositoryOutputPort);
    }

    @Bean
    public CadastrarUsuarioInputPort cadastrarUsuarioInputPort(UsuarioRepositoryOutputPort usuarioRepositoryOutputPort
                                                            ,   PasswordEncoder passwordEncoder) {
        return new CadastrarUsuarioUseCase(usuarioRepositoryOutputPort, passwordEncoder);
    }

}
