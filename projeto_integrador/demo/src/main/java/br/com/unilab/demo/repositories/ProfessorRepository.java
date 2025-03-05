package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
