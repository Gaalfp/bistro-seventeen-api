package br.com.techchallenge.bistro.seventeen.port.input;

import br.com.techchallenge.bistro.seventeen.adapter.input.controller.dto.TrocarSenhaRequestDTO;

import java.util.UUID;

public interface TrocarSenhaInputPort {

    void trocarSenha(UUID id, TrocarSenhaRequestDTO dto);
}
