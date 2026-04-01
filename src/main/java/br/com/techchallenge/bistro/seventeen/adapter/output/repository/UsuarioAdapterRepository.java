package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioAdapterRepository implements UsuarioRepositoryOutputPort {

    private final UsariorRepository usariorRepository;

}
