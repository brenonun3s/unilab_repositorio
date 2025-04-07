package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interface de acesso ao banco de dados para persistir os Professores no Banco de Dados
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
}
