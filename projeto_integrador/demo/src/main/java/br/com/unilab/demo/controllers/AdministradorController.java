package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.ProfessorNaoLocalizadoException;
import br.com.unilab.demo.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    //TODO: OS MÉTODOS DESSA CLASSE AINDA NÃO FORAM TESTADOS!

    @Autowired
    private AdministradorService administradorService;

    private static void AgendamentoNaoLocalizado() {
        throw new AgendamentoNaoLocalizadoException("Para deletar, é necessário que o agendamento esteja cadastrad");
    }

    @PostMapping("/cadastrar-professor")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor professor) {
        try {
            administradorService.criarUsuario(professor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Professor> deletarUsuario(@PathVariable Long id, Professor professor) {
        try {
            administradorService.buscarProfessor(id);

            if (professor.getId() == null) {
                throw new ProfessorNaoLocalizadoException("Professor não localizado!");
            }

            administradorService.deletarUsuario(professor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{/id}")
    public ResponseEntity<Professor> atualizarUsuario(@PathVariable Long id, @RequestBody Professor professor) {
        try {
            administradorService.buscarProfessor(id);

            if (professor.getId() == null) {
                throw new ProfessorNaoLocalizadoException("Professor não localizado com esse ID!");
            }
            administradorService.atualizarUsuario(professor);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Professor> listarProfessores() {
        try {
            administradorService.listarProfessores();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o suporte!");
        }
    }

    @PostMapping("/cadastrar-laboratorio")
    public ResponseEntity<Laboratorio> cadastrarLaboratorio(@RequestBody Laboratorio laboratorio) {
        try {
            administradorService.criarLaboratorio(laboratorio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Laboratorio> deletarLaboratorio(@PathVariable Long id, Laboratorio laboratorio) {
        try {
            administradorService.buscarLaboratorio(id);

            if (laboratorio.getId() == null) {
                throw new LaboratorioNaoLocalizadoException("Laboratório não localizado com esse ID");
            }

            administradorService.deletarLaboratorio(laboratorio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{/id}")
    public ResponseEntity<Laboratorio> atualizarLaboratorio(@PathVariable Long id, Laboratorio laboratorio) {
        try {
            administradorService.buscarLaboratorio(id);

            if (laboratorio.getId() == null) {
                throw new LaboratorioNaoLocalizadoException("Para atualizar, é necessário que o Laboratório esteja salvo na base");
            }

            administradorService.atualizarLaboratorio(laboratorio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Laboratorio> listarLaboratorios() {
        try {
            administradorService.listarLaboratorios();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o suporte!");
        }
    }

    @PostMapping("/novo-agendamento")
    public ResponseEntity<Agendamento> criarNovoAgendamento(@RequestBody Agendamento agendamento) {
        try {
            administradorService.criarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Agendamento> deletarAgendamento(@PathVariable Long id, Agendamento agendamento) {
        try {
            administradorService.buscarAgendamento(id);
            if (agendamento.getId() == null) {
                AgendamentoNaoLocalizado();
            }
            administradorService.deletarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{/id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, Agendamento agendamento) {
        try {
            administradorService.buscarAgendamento(id);
            if (agendamento.getId() == null) {
                AgendamentoNaoLocalizado();
            }
            administradorService.atualizarAgendamento(agendamento);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Agendamento> listarAgendamentos() {
        try {
            administradorService.listarAgendamentos();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o suporte!");
        }
    }

}




