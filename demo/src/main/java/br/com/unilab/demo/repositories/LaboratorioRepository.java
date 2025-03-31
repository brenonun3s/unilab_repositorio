package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interface de acesso ao banco de dados para persistir os Laboratorios no Banco de Dados
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

public interface LaboratorioRepository extends JpaRepository<Laboratorio, UUID> {
}
