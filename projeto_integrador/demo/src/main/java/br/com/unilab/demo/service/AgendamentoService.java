package br.com.unilab.demo.service;

import br.com.unilab.demo.entities.Agendamento;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void buscarTodosAgendamentos() {
        agendamentoRepository.findAll();
    }

    public Optional<Agendamento> obterPorId(Long id) {
        return agendamentoRepository.findById(id);
    }


}
