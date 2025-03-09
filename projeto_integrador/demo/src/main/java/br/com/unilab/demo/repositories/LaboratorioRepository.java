package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, UUID> {
}
