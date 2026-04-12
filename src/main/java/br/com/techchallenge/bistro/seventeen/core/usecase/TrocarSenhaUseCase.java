package br.com.techchallenge.bistro.seventeen.core.usecase;

import br.com.techchallenge.bistro.seventeen.adapter.exception.EntidadeNaoEncontradaException;
import br.com.techchallenge.bistro.seventeen.adapter.exception.SenhaAtualIncorretaException;
import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.TrocarSenhaRequest;
import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import br.com.techchallenge.bistro.seventeen.port.input.TrocarSenhaInputPort;
import br.com.techchallenge.bistro.seventeen.port.output.UsuarioRepositoryOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class TrocarSenhaUseCase implements TrocarSenhaInputPort {

    private final UsuarioRepositoryOutputPort repository;
    private final PasswordEncoder passwordEncoder;

    public TrocarSenhaUseCase(UsuarioRepositoryOutputPort repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void trocarSenha(UUID id, TrocarSenhaRequest dto) {
        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com id: " + id + " não encotrado!"));

        if (!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenhaHash())) {
            throw new SenhaAtualIncorretaException("Senha atual inválida");
        }

        usuario.setSenhaHash(passwordEncoder.encode(dto.novaSenha()));

        repository.salvar(usuario);
    }
}
