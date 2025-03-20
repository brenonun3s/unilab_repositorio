package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe Controller dos Professor do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/login")
    public String loginProf() {
        return "telaDeLoginProfessor";
    }

    //TESTAR ROTA
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/solicitar-agendamento")
    public ResponseEntity<Agendamento> solicitar(@RequestBody Agendamento agendamento) {
        try {
            professorService.solicitarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-agendamento/{id}")
    public ResponseEntity<Object> deletarAgendamento(@PathVariable("id") String id) {
        return professorService.buscarAgendamento(UUID.fromString(id))
                .map(agendamento -> {
                    professorService.deletarAgendamento(agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/atualizar-agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(@PathVariable("id") String id, @RequestBody Agendamento agendamento) {
        return professorService.buscarAgendamento(UUID.fromString(id))
                .map(agendamentoExistente -> {
                    professorService.atualizarAgendamento(agendamentoExistente, agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }


}

