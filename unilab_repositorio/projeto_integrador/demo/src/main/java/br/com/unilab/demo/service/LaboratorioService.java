package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.repositories.LaboratorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por fornecer os serviços relacionados à entidade {@link Laboratorio}.
 * Contém os métodos de criação, exclusão, atualização, listagem e busca dos Laboratórios.
 * Utiliza {@link LaboratorioRepository} para persistência dos dados.
 *
 * @author Breno Nunes
 * @see br.com.unilab.demo.model.entities.Agendamento
 * @since 20/03/2025
 */


@Service
@RequiredArgsConstructor
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

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
        laboratorioRepository.save(laboratorioExistente);
    }

    public Optional<Laboratorio> buscarLaboratorio(Long id) {
        return laboratorioRepository.findById(id);
    }

    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }




}



