package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioOcupadoException;
import br.com.unilab.demo.model.support.SolicitacoesSuporte;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final AgendamentoRepository agendamentoRepository;

    private final AudioVisualService audioVisualService;

    public Agendamento solicitarAgendamento(Agendamento agendamento, Laboratorio laboratorio) {
        if (!laboratorio.isStatusLaboratorio()) {
            throw new LaboratorioOcupadoException("Não é possível agendar! Laboratório ocupado!");
        } else {
            return agendamentoRepository.save(agendamento);
        }
    }

    public void deletarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException("Para excluir, é necessário que o Agendamento esteja cadastrado!");
        }
        agendamentoRepository.delete(agendamento);
    }

    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException("Para atualizar, é necessário que o Agendamento esteja cadastrado!");
        }
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    //TODO: MELHORAR!
    public void contatarAudioVisual() {
        audioVisualService.contatarAudioVisual(new SolicitacoesSuporte());

    }

}
