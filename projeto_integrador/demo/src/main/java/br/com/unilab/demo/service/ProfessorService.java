package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioOcupadoException;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import br.com.unilab.demo.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe Service que processa as funções do Professor
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    // CRUD PROFESSOR

    @Transactional
    public Professor criarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public void deletarProfessor(Professor professor) {
        if (professor.getId() == null || professor.getNome() == null) {
            throw new IllegalArgumentException("Para excluir, é necessário que o Usuário esteja cadastrado!");
        }
        professorRepository.delete(professor);
    }

    @Transactional
    public void atualizarProfessor(Professor professorExistente, Professor professorAtualizacao) {
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




}
