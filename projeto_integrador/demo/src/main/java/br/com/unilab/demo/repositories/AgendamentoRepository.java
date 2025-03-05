package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
