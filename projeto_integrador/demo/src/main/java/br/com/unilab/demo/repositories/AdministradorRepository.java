package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
