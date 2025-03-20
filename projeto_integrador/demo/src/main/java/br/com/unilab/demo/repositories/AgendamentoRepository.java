package br.com.unilab.demo.repositories;

import br.com.unilab.demo.model.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * Interface de acesso ao banco de dados para persistir os Agendamentos no Banco de Dados
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

    String findByDataAgendamentoAndAndHorario();
}
