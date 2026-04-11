package br.com.techchallenge.bistro.seventeen.port.input;

public interface ValidarLoginInputPort {
    String executar(String login, String senhaEmTextoPlano);
}