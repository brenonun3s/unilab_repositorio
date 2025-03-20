package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.model.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    AdministradorService administradorService;

    @GetMapping("/login")
    public String loginAdmin() {
        return "telaDeLoginAdministrador";
    }


    //OK
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar-professor")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor professor) {
        try {
            administradorService.criarUsuario(professor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-professor/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable("id") String id) {
        return administradorService.buscarProfessor(UUID.fromString(id))
                .map(professor -> {
                    administradorService.deletarUsuario(professor);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/atualizar-professor/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") String id, @RequestBody Professor professor) {
        return administradorService.buscarProfessor(UUID.fromString(id))
                .map(professorExistente -> {
                    administradorService.atualizarUsuario(professorExistente, professor);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/listar-professores")
    public ResponseEntity<List<Professor>> listarProfessores() {
        try {
            List<Professor> professores = administradorService.listarProfessores();
            if (professores.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(professores);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o suporte!");
        }
    }

   //OK
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar-laboratorio")
    public ResponseEntity<Laboratorio> cadastrarLaboratorio(@RequestBody Laboratorio laboratorio) {
        try {
            administradorService.criarLaboratorio(laboratorio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-laboratorio/{id}")
    public ResponseEntity<Object> deletarLaboratorio(@PathVariable("id") String id) {
        return administradorService.buscarLaboratorio(UUID.fromString(id))
                .map(laboratorio -> {
                    administradorService.deletarLaboratorio(laboratorio);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //OK
    @PutMapping("/atualizar-laboratorio/{id}")
    public ResponseEntity<Object> atualizarLaboratorio(@PathVariable("id") String id, @RequestBody Laboratorio laboratorio) {
        return administradorService.buscarLaboratorio(UUID.fromString(id))
                .map(laboratorioExistente -> {
                    administradorService.atualizarLaboratorio(laboratorioExistente, laboratorio);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    //OK
    @GetMapping("/listar-laboratorios")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        try {
            List<Laboratorio> laboratorios = administradorService.listarLaboratorios();
            if (laboratorios.isEmpty()) {
                throw new LaboratorioNaoLocalizadoException("Não possui laboratórios cadastrados!");
            }
            return ResponseEntity.ok(laboratorios);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o suporte!");
        }
    }

    // TESTAR ROTA!!!
    @PostMapping("/novo-agendamento")
    public ResponseEntity<Agendamento> criarNovoAgendamento(@RequestBody Agendamento agendamento) {
        try {
            administradorService.criarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deletar-agendamento/{id}")
    public ResponseEntity<Agendamento> deletarAgendamento(@PathVariable UUID id, Agendamento agendamento) {
        try {
            administradorService.buscarAgendamento(id);
            if (agendamento.getId() == null) {
                throw new AgendamentoNaoLocalizadoException("Não localizado");
            }
            administradorService.deletarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar-agendamento/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable UUID id, Agendamento agendamento) {
        try {
            administradorService.buscarAgendamento(id);
            if (agendamento.getId() == null) {
                throw new RuntimeException("Não localizado");
            }
            administradorService.atualizarAgendamento(agendamento);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar-agendamentos")
    public ResponseEntity<Agendamento> listarAgendamentos() {
        try {
            administradorService.listarAgendamentos();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o suporte!");
        }
    }


}