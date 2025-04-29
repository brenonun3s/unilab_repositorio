package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/solicitar-agendamento")
    public ResponseEntity<Agendamento> solicitar(@RequestBody Agendamento agendamento) {
        try {
            agendamentoService.solicitarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-agendamento/{id}")
    public ResponseEntity<Object> deletarAgendamento(@PathVariable("id") Long id) {
        return agendamentoService.buscarAgendamento(id)
                .map(agendamento -> {
                    agendamentoService.deletarAgendamento(agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/atualizar-agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(@PathVariable("id") Long id, @RequestBody Agendamento agendamento) {
        return agendamentoService.buscarAgendamento(id)
                .map(agendamentoExistente -> {
                    agendamentoService.atualizarAgendamento(agendamentoExistente, agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/listar-agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
            if (agendamentos.isEmpty()) {
                throw new AgendamentoNaoLocalizadoException("Não possui laboratórios cadastrados!");
            }
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o sobre!");
        }
    }
}
