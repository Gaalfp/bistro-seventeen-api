package br.com.techchallenge.bistro.seventeen.adapter.output.repository;

import br.com.techchallenge.bistro.seventeen.adapter.output.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsariorRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}
