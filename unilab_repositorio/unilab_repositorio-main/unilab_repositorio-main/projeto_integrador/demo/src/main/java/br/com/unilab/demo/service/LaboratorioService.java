package br.com.unilab.demo.service;

import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.repositories.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;


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



