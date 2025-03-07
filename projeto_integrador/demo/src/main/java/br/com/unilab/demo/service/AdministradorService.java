package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import br.com.unilab.demo.repositories.LaboratorioRepository;
import br.com.unilab.demo.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor -> Dev Breno Nunes
 */

@RequiredArgsConstructor
@Service
public class AdministradorService {

    private final AgendamentoRepository agendamentoRepository;

    private final LaboratorioRepository laboratorioRepository;

    private final ProfessorRepository professorRepository;


    // CRUD LABORATÓRIO
    public Laboratorio criarLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public void deletarLaboratorio(Laboratorio laboratorio) {
        laboratorioRepository.delete(laboratorio);
    }

    public Laboratorio atualizarLaboratorio(Laboratorio laboratorio) {
        if (laboratorio.getId() == null || laboratorio.getNumeroLaboratorio() == null) {
            throw new LaboratorioNaoLocalizadoException(
                    "Para atualizar, é necessário que o Laboratório esteja cadastrado!");
        }
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio buscarLaboratorio(Long id) {
        return laboratorioRepository.findById(id).orElse(null);
    }

    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }

    // CRUD AGENDAMENTO

    public Agendamento criarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException(
                    "Para excluir, é necessário que o Agendamento esteja cadastrado!");
        }
        agendamentoRepository.delete(agendamento);
    }

    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException("Para atualizar, é necessário que o Agendamento esteja cadastrado!");
        }
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarAgendamento(Long id) {
        return agendamentoRepository.findById(id).orElse(null);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    // CRUD USUARIO

    public Professor criarUsuario(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deletarUsuario(Professor professor) {
        if (professor.getId() == null || professor.getNome() == null) {
            throw new IllegalArgumentException("Para excluir, é necessário que o Usuário esteja cadastrado!");
        }
        professorRepository.delete(professor);
    }

    public Professor atualizarUsuario(Professor professor) {
        if (professor.getId() == null || professor.getNome() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o Usuário esteja cadastrado!");
        }
        return professorRepository.save(professor);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor buscarProfessor(Long id) {
        return professorRepository.findById(id).orElse(null);
    }


}
