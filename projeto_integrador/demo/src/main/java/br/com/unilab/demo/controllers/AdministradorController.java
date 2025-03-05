package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Agendamento;
import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.entities.Professor;
import br.com.unilab.demo.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<Professor> cadastrarUsuario(@RequestBody Professor professor) {
        try {
            administradorService.criarUsuario(professor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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
    public ResponseEntity<Professor> deletarUsuario(@PathVariable Long id, Professor professor) {
        try {
            administradorService.deletarUsuario(professor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Laboratorio> deletarUsuario(@PathVariable Long id, Laboratorio laboratorio) {
        try {
            administradorService.deletarLaboratorio(laboratorio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoService.obterPorId(id);

        if (agendamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendamento.setNumeroLaboratorio(agendamentoOptional.get().getNumeroLaboratorio());
        agendamento.setDataAgendamento(agendamentoOptional.get().getDataAgendamento());
        agendamento.setUsuarioAgendado(agendamentoOptional.get().getUsuarioAgendado());

        administradorService.criarUsuario(usuario);

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarLaboratorio(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoService.obterPorId(id);

        if (agendamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendamento.setNumeroLaboratorio(agendamentoOptional.get().getNumeroLaboratorio());
        agendamento.setDataAgendamento(agendamentoOptional.get().getDataAgendamento());
        agendamento.setUsuarioAgendado(agendamentoOptional.get().getUsuarioAgendado());

        agendamentoRepository.save(agendamento);

        return ResponseEntity.ok().build();
    }

    //TODO: MELHORAR METODO, NAO ESTA SALVANDO DADOS, APENAS ID
    @PostMapping
    public ResponseEntity<Agendamento> efetuarAgendamento(@RequestBody Agendamento agendamento) {
        try {
            usuarioService.solicitarAgendamento(agendamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO: TESTAR MÉTODO, AINDA NAO TESTADO
    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Optional<Agendamento> agendamentoOptional = agendamentoService.obterPorId(id);

        if (agendamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendamento.setNumeroLaboratorio(agendamentoOptional.get().getNumeroLaboratorio());
        agendamento.setDataAgendamento(agendamentoOptional.get().getDataAgendamento());
        agendamento.setUsuarioAgendado(agendamentoOptional.get().getUsuarioAgendado());

        agendamentoRepository.save(agendamento);

        return ResponseEntity.ok().build();
    }

    //TODO: TESTAR MÉTODO, AINDA NAO TESTADO
    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        Optional<Agendamento> agendamentoOptional = agendamentoService.obterPorId(id);
        if (agendamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        agendamentoRepository.delete(agendamentoOptional.get());
        return ResponseEntity.noContent().build();
    }

    //TODO: TESTAR MÉTODO, AINDA NAO TESTADO
    @GetMapping
    public ResponseEntity<Void> listar() {
        agendamentoService.buscarTodosAgendamentos();
        return ResponseEntity.ok().build();
    }


}


