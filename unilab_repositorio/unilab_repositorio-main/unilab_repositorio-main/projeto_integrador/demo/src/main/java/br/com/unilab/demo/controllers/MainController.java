package br.com.unilab.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

    @GetMapping("/seja-bem-vindo")
    public String sejaBemVindo(){
        return "sejaBemVindo";
    }

    @GetMapping("agendar-laboratorio")
    public String agendarLaboratorio(){
        return "agendamento_lab";
    }

    @GetMapping("historico")
    public String historico(){
        return "historico";
    }

    @GetMapping("gerenciar-professor")
    public String gerenciarProfessor(){
        return "administradorgerenciarprof";
    }

    @GetMapping("gerenciar-laboratorio")
    public String gerenciarLaboratorio(){
        return "administradorgerenciarlab";
    }
}
