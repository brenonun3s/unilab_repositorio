package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.Agendamento;
import br.com.unilab.demo.model.Professor;
import br.com.unilab.demo.service.AgendamentoService;
import br.com.unilab.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller dos Professores do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/login")
    public String loginProf() {
        return "telaDeLoginProfessor";
    }

    @GetMapping("/novo-agendamento")
    public String novoAgendamento(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        return "meusagendamentos";
    }

    // CRUD PROFESSORES ----------

    @PostMapping("/cadastrar-professor")
    public String solicitarPorFormulario(@ModelAttribute Professor professor) {
        professorService.criarProfessor(professor);
        return "redirect:/main/gerenciar-professor";
    }
}
