package br.com.unilab.demo.controllers;

import br.com.unilab.demo.model.entities.Laboratorio;
import br.com.unilab.demo.model.exceptions.LaboratorioNaoLocalizadoException;
import br.com.unilab.demo.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class LaboratorioController {

    @Autowired
    LaboratorioService laboratorioService;

    //OK
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar-laboratorio")
    public ResponseEntity<Laboratorio> cadastrarLaboratorio(@RequestBody Laboratorio laboratorio) {
        try {
            laboratorioService.criarLaboratorio(laboratorio);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-laboratorio/{id}")
    public ResponseEntity<Object> deletarLaboratorio(@PathVariable("id") Long id) {
        return laboratorioService.buscarLaboratorio(id)
                .map(laboratorio -> {
                    laboratorioService.deletarLaboratorio(laboratorio);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //OK
    @PutMapping("/atualizar-laboratorio/{id}")
    public ResponseEntity<Object> atualizarLaboratorio(@PathVariable("id") Long id, @RequestBody Laboratorio laboratorio) {
        return laboratorioService.buscarLaboratorio(id)
                .map(laboratorioExistente -> {
                    laboratorioService.atualizarLaboratorio(laboratorioExistente, laboratorio);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    //OK
    @GetMapping("/listar-laboratorios")
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        try {
            List<Laboratorio> laboratorios = laboratorioService.listarLaboratorios();
            if (laboratorios.isEmpty()) {
                throw new LaboratorioNaoLocalizadoException("Não possui laboratórios cadastrados!");
            }
            return ResponseEntity.ok(laboratorios);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o sobre!");
        }
    }

}
