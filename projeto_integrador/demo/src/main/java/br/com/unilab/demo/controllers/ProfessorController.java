package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/solicitar-agendamento")
    public ResponseEntity<Agendamento> solicitarAgendamento(@RequestBody Agendamento agendamento) {
        try {
            professorService.solicitarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //ATUALIZAR AGENDAMNETO

    //CANCELAR AGENDAMENTO


    //METODO QUE BUSQUE APENAS O LABORATORIO COMO TRUE EM DISPONIVEL

    // IMPLEMENTAR LOGICA QUE QUANDO SALVAR O AGENDAMENTO, O LAB FIQUE INDISPONIVEL

    //IMPLEMENTAR UMA PESQUISA PAGINA COM TODOS OS AGENDAMENTOS

    // IMPLEMENTAR UMA PESQUISA PAGINA APENAS COM OS LABORATORIOS DISPONIVEIS

}

