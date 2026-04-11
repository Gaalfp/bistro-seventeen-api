package br.com.techchallenge.bistro.seventeen.port.input;

import br.com.techchallenge.bistro.seventeen.core.model.Usuario;
import java.util.List;

public interface BuscarUsuarioInputPort {
    List<Usuario> buscarTodos();

    List<Usuario> buscarPorNome(String nome);
}