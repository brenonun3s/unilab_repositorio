package br.com.unilab.demo.service;

import br.com.unilab.demo.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.exceptions.LaboratorioOcupadoException;
import br.com.unilab.demo.model.Agendamento;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por fornecer os serviços relacionados à entidade {@link Agendamento}.
 * Contém os métodos de criação, exclusão, atualização, listagem e busca dos agendamentos.
 * Utiliza {@link AgendamentoRepository} para persistência dos dados.
 *
 * @author Breno Nunes
 * @see Agendamento
 * @since 20/03/2025
 */

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    public Agendamento solicitarAgendamento(Agendamento agendamento) {
        Agendamento ag = agendamentoRepository.findByData(agendamento.getData());

        if (ag != null) {
            throw new LaboratorioOcupadoException("Não é possível agendar! Laboratório ocupado!");
        }
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void deletarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException("Para excluir, é necessário que o Agendamento esteja cadastrado!");
        }
        agendamentoRepository.delete(agendamento);
    }

    @Transactional
    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException("Para atualizar, é necessário que o Agendamento esteja cadastrado!");
        }
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarAgendamento(Long id) {
        return agendamentoRepository.findById(id);
    }

    @Transactional
    public void atualizarAgendamento(Agendamento agendamentoExistente, Agendamento agendamentoAtualizacao) {
        if (agendamentoExistente.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o Agendamento esteja cadastrado na base!");
        }

        if (agendamentoAtualizacao.getProfessor() != null) {
            agendamentoExistente.setProfessor(agendamentoAtualizacao.getProfessor());
        }
        if (agendamentoAtualizacao.getData() != null) {
            agendamentoExistente.setData(agendamentoAtualizacao.getData());
        }
        agendamentoRepository.save(agendamentoExistente);
    }

}
