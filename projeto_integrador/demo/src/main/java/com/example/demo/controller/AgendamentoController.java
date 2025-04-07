package com.example.demo.controller;

import com.example.demo.model.Agendamento;
import com.example.demo.model.Laboratorio;
import com.example.demo.model.Professor;
import com.example.demo.service.AgendamentoService;
import com.example.demo.service.LaboratorioService;
import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/professores")
    @ResponseBody
    public List<Professor> listarProfessores() {
        return professorService.listarTodos();
    }

    @GetMapping("/laboratorios")
    @ResponseBody
    public List<Laboratorio> listarLaboratorios() {
        return laboratorioService.listarTodos();
    }

    @GetMapping("/agendamentos/{laboratorioId}")
    @ResponseBody
    public List<Agendamento> listarAgendamentosPorLaboratorio(@PathVariable Long laboratorioId) {
        return agendamentoService.listarPorLaboratorio(laboratorioId);
    }

    @PostMapping("/agendamentos")
    @ResponseBody
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento novoAgendamento = agendamentoService.salvar(agendamento);
        return ResponseEntity.ok(novoAgendamento);
    }

    @DeleteMapping("/agendamentos/{id}")
    @ResponseBody
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return ResponseEntity.ok().build();
    }
} 