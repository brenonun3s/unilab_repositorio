package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.service.AdministradorService;
import br.com.unilab.demo.service.LaboratorioService;
import br.com.unilab.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Classe Controller dos ADM do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */


@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private LaboratorioService laboratorioService;

    @GetMapping("/login")
    public String loginAdmin() {
        return "telaDeLoginAdministrador";
    }

    @GetMapping("/gerenciar-professores")
    public String gerenciarProfessores(Model model) {
        return "administradorGerenciarProfessores";
    }

    @GetMapping("/listar")
    public String listarProfessores(Model model) {
        List<Professor> professores = professorService.listarProfessores();
        model.addAttribute("professores", professores);
        return "administradorGerenciarProfessores";
    }

    @GetMapping("/novo-laboratorio")
    public String novoLaboratorio(Model model) {
        model.addAttribute("laboratorio", new Laboratorio());
        return "administradorGerenciarLaboratorios";
    }

    @GetMapping("/novo-professor")
    public String novoProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "administradorGerenciarProfessores";
    }

    @GetMapping("/listar-todos-agendamentos")
    public String listarAgendamentos(Model model) {
        List<Laboratorio> laboratorios = laboratorioService.listarLaboratorios();
        model.addAttribute("laboratorios", laboratorios);
        return "administradorGerenciarLaboratorios";
    }

    @GetMapping("/novo-agendamento")
    public String novoAgendamento(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        return "agendamento_lab";
    }


}