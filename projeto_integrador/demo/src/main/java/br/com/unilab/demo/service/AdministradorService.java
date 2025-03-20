package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.FerramentasDisponiveis;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import br.com.unilab.demo.repositories.FerramentasRepository;
import br.com.unilab.demo.repositories.LaboratorioRepository;
import br.com.unilab.demo.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe Service que processa as funções do Administrador*
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */
@Service
public class AdministradorService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    LaboratorioRepository laboratorioRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private FerramentasRepository ferramentasRepository;

    // CRUD LABORATÓRIO

    @Transactional
    public Laboratorio criarLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Transactional
    public void deletarLaboratorio(Laboratorio laboratorio) {
        if (laboratorio.getId() == null || laboratorio.getNumeroLaboratorio() == null) {
            throw new IllegalArgumentException("Para excluir, é necessário que o Laboratório esteja cadastrado!");
        }
        laboratorioRepository.delete(laboratorio);
    }

    @Transactional
    public void atualizarLaboratorio(Laboratorio laboratorioExistente, Laboratorio laboratorioAtualizacao) {
        if (laboratorioExistente.getId() == null || laboratorioExistente.getNumeroLaboratorio() == null) {
            throw new LaboratorioNaoLocalizadoException(
                    "Para atualizar, é necessário que o Laboratório esteja cadastrado!");
        }
        if (laboratorioAtualizacao.getNumeroLaboratorio() != null) {
            laboratorioExistente.setNumeroLaboratorio(laboratorioAtualizacao.getNumeroLaboratorio());
        }
        if (laboratorioAtualizacao.getDepartamentoLaboratorio() != null) {
            laboratorioExistente.setDepartamentoLaboratorio(laboratorioAtualizacao.getDepartamentoLaboratorio());
        }
        if (laboratorioAtualizacao.getFerramentasDisponiveis() != null) {
            laboratorioExistente.setFerramentasDisponiveis(laboratorioAtualizacao.getFerramentasDisponiveis());
        }

        laboratorioRepository.save(laboratorioExistente);
    }

    public Optional<Laboratorio> buscarLaboratorio(UUID id) {
        return laboratorioRepository.findById(id);
    }

    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }

    // CRUD AGENDAMENTO

    @Transactional
    public Agendamento criarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void deletarAgendamento(Agendamento agendamento) {
        if (agendamento.getId() == null) {
            throw new AgendamentoNaoLocalizadoException(
                    "Para excluir, é necessário que o Agendamento esteja cadastrado!");
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

    public Agendamento buscarAgendamento(UUID id) {
        return agendamentoRepository.findById(id).orElse(null);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    // CRUD USUARIO

    @Transactional
    public Professor criarUsuario(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public void deletarUsuario(Professor professor) {
        if (professor.getId() == null || professor.getNome() == null) {
            throw new IllegalArgumentException("Para excluir, é necessário que o Usuário esteja cadastrado!");
        }
        professorRepository.delete(professor);
    }

    @Transactional
    public void atualizarUsuario(Professor professorExistente, Professor professorAtualizacao) {
        if (professorExistente.getId() == null || professorExistente.getNome() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o Usuário esteja cadastrado!");
        }

        if (professorAtualizacao.getNome() != null) {
            professorExistente.setNome(professorAtualizacao.getNome());
        }
        if (professorAtualizacao.getSenha() != null) {
            professorExistente.setSenha(professorAtualizacao.getSenha());
        }
        if (professorAtualizacao.getMatricula() != null) {
            professorExistente.setMatricula(professorAtualizacao.getMatricula());
        }
        if (professorAtualizacao.getEmail() != null) {
            professorExistente.setEmail(professorAtualizacao.getEmail());
        }

        professorRepository.save(professorExistente);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessor(UUID id) {
        return professorRepository.findById(id);

    }

    @Transactional
    public FerramentasDisponiveis salvar (FerramentasDisponiveis ferramentasDisponiveis) {
        return ferramentasRepository.save(ferramentasDisponiveis);
    }

    @Transactional
    public void deletarFerramenta(FerramentasDisponiveis ferramentasDisponiveis) {
        if (ferramentasDisponiveis.getId() == null || ferramentasDisponiveis.getNome() == null) {
            throw new IllegalArgumentException("Para excluir, é necessário que a Ferramenta esteja cadastrado!");
        }
        ferramentasRepository.delete(ferramentasDisponiveis);
    }


}
