package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
}
