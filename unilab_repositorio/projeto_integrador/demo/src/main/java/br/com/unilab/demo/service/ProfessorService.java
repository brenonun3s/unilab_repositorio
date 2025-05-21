package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por fornecer os serviços relacionados à entidade {@link Professor}.
 * Contém os métodos de criação, exclusão, atualização, listagem e busca de professores.
 * Utiliza {@link ProfessorRepository} para persistência dos dados.
 *
 * @author Breno Nunes
 * @see br.com.unilab.demo.model.entities.Professor
 * @since 20/03/2025
 */

@Service
@RequiredArgsConstructor
public class ProfessorService {


    private final ProfessorRepository professorRepository;

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
        if (professorAtualizacao.getEmail() != null) {
            professorExistente.setEmail(professorAtualizacao.getEmail());
        }

        professorRepository.save(professorExistente);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessor(Long id) {
        return professorRepository.findById(id);

    }




}
