package br.com.unilab.demo.service;

import br.com.unilab.demo.entities.Laboratorio;
import br.com.unilab.demo.repositories.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public List<Laboratorio> visualizarLaboratorios() {
        return laboratorioRepository.findAll();
    }


}
