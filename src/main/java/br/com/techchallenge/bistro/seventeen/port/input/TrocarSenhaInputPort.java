package br.com.techchallenge.bistro.seventeen.port.input;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.request.TrocarSenhaRequest;

import java.util.UUID;

public interface TrocarSenhaInputPort {

    void trocarSenha(UUID id, TrocarSenhaRequest dto);
}
