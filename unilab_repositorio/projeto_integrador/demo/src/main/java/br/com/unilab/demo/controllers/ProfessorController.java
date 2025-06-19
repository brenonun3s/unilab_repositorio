package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.service.AgendamentoService;
import br.com.unilab.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe Controller dos Professores do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/login")
    public String loginProf() {
        return "telaDeLoginProfessor";
    }

    @GetMapping("/listar-todos-agendamentos")
    public String listarAgendamentos(Model model) {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
        model.addAttribute("agendamentos", agendamentos);
        return "historico";
    }

    @GetMapping("/novo-agendamento")
    public String novoAgendamento(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        return "meusagendamentos";
    }

    //CRUD PROFESSORES ----------

    //OK
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar-professor")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor professor) {
        try {
            professorService.criarProfessor(professor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-professor/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable("id") Long id) {
        return professorService.buscarProfessor(id)
                .map(professor -> {
                    professorService.deletarProfessor(professor);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/atualizar-professor/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Professor professor) {
        return professorService.buscarProfessor(id)
                .map(professorExistente -> {
                    professorService.atualizarProfessor(professorExistente, professor);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/listar-professores")
    public ResponseEntity<List<Professor>> listarProfessores() {
        try {
            List<Professor> professores = professorService.listarProfessores();
            if (professores.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(professores);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o sobre!");
        }
    }
}





