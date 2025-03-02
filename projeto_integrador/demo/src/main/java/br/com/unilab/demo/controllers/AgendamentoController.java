package br.com.unilab.demo.controllers;

import br.com.unilab.demo.entities.Agendamento;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import br.com.unilab.demo.service.AgendamentoService;
import br.com.unilab.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    //TODO: MELHORAR METODO, NAO ESTA SALVANDO DADOS, APENAS ID
    @PostMapping
    public ResponseEntity<Agendamento> efetuarAgendamento(@RequestBody Agendamento agendamento) {
        try {
            usuarioService.solicitarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO: TESTAR MÉTODO, AINDA NAO TESTADO
    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoService.obterPorId(id);

        if (agendamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendamento.setNumeroLaboratorio(agendamentoOptional.get().getNumeroLaboratorio());
        agendamento.setDataAgendamento(agendamentoOptional.get().getDataAgendamento());
        agendamento.setUsuarioAgendado(agendamentoOptional.get().getUsuarioAgendado());

        agendamentoRepository.save(agendamento);

        return ResponseEntity.ok().build();
    }

    //TODO: TESTAR MÉTODO, AINDA NAO TESTADO
    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        Optional<Agendamento> agendamentoOptional = agendamentoService.obterPorId(id);
        if (agendamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        agendamentoRepository.delete(agendamentoOptional.get());
        return ResponseEntity.noContent().build();
    }

    //TODO: TESTAR MÉTODO, AINDA NAO TESTADO
    @GetMapping
    public ResponseEntity<Void> listar() {
        agendamentoService.buscarTodosAgendamentos();
        return ResponseEntity.ok().build();
    }




    }
