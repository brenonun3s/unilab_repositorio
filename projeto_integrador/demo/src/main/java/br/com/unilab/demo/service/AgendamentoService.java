package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioOcupadoException;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public Agendamento solicitarAgendamento(Agendamento agendamento) {
        if (Objects.equals(agendamentoRepository.
                findByDataAgendamentoAndAndHorario(), agendamento.getHorario()) ||
                Objects.equals(agendamentoRepository.
                        findByDataAgendamentoAndAndHorario(), agendamento.getData())) {
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

    public Optional<Agendamento> buscarAgendamento(UUID id) {
        return agendamentoRepository.findById(id);
    }

    @Transactional
    public void atualizarAgendamento(Agendamento agendamentoExistente, Agendamento agendamentoAtualizacao) {
        if (agendamentoExistente.getId() == null || agendamentoExistente.getNumerolaboratorio() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o Agendamento esteja cadastrado na base!");
        }

        if (agendamentoAtualizacao.getProfessor() != null) {
            agendamentoExistente.setProfessor(agendamentoAtualizacao.getProfessor());
        }
        if (agendamentoAtualizacao.getData() != null) {
            agendamentoExistente.setData(agendamentoAtualizacao.getData());
        }
        if (agendamentoAtualizacao.getNumerolaboratorio() != null) {
            agendamentoExistente.setNumerolaboratorio(agendamentoAtualizacao.getNumerolaboratorio());
        }
        if (agendamentoAtualizacao.getHorario() != null) {
            agendamentoExistente.setHorario(agendamentoAtualizacao.getHorario());
        }
        agendamentoRepository.save(agendamentoExistente);
    }






}
