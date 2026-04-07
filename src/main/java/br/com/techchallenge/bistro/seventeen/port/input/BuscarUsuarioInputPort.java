package br.com.techchallenge.bistro.seventeen.port.input;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;

public interface BuscarUsuarioInputPort {

    Usuario buscarPorNome(String nome);
}
