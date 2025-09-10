package br.com.unilab.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.unilab.demo.model.Agendamento;
import br.com.unilab.demo.model.Professor;
import br.com.unilab.demo.model.Laboratorio;

/**
 * Classe Controller que carregará apenas os templates
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

@Controller
@RequestMapping("main")
public class MainController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "/sobre";
    }

    @GetMapping("/tutoriais")
    public String tutoriais() {
        return "tutoriais";
    }

    @GetMapping("/suporte")
    public String suporte(){
        return "documento";
    }

    @GetMapping("/login-admin")
    public String telaLoginAdm(){
        return "telaDeLoginAdministrador";
    }

    @GetMapping("/login-professor")
    public String telaLoginProfessor(){
        return "telaDeLoginProfessor";
    }

    @PostMapping("/processar-login")
    public String processarLoginAdm(){
        return "redirect:/main/seja-bem-vindo";
    }

    @GetMapping("/seja-bem-vindo-adm")
    public String sejaBemVindo(){
        return "sejabemvindoadm";
    }

    @GetMapping("/seja-bem-vindo-professor")
    public String sejaBemVindoProfessor(){
        return "sejabemvindoprofessor";
    }

    @GetMapping("/agendar-laboratorio")
    public String agendarLaboratorio(Model model){
        model.addAttribute("agendamento", new Agendamento());
        return "cadastraragendamento";
    }

    @GetMapping("/historico")
    public String historico(){
        return "historico";
    }

    @GetMapping("/cadastrar-professor")
    public String cadastrarProfessor(Model model){
        model.addAttribute("professor", new Professor());
        return "cadastarprofessor";
    }

    @GetMapping("/cadastrar-laboratorio")
    public String cadastrarLaboratorio(Model model){
        model.addAttribute("laboratorio", new Laboratorio());
        return "cadastrarlaboratorio";
    }

    @GetMapping("/atualizar-professor")
    public String atualizarProfessor(){
        return "atualizarprofessor";
    }

    @GetMapping("/atualizar-laboratorio")
    public String atualizarLaboratorio(){
        return "atualizarlaboratorio";
    }

    @GetMapping("gerenciar-laboratorio")
    public String gerenciarLaboratorio(){
        return "admgerenciarlab";
    }

    @GetMapping("gerenciar-professor")
    public String gerenciarProfessor(){
        return "admgerenciarprof";
    }

    @GetMapping("meus-agendamentos")
    public String meusAgendamentos(){
        return "meusagendamentos";
    }

}
