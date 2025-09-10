package br.com.unilab.demo.controllers;

import br.com.unilab.demo.exceptions.AgendamentoNaoLocalizadoException;
import br.com.unilab.demo.model.Agendamento;
import br.com.unilab.demo.model.Usuario;
import br.com.unilab.demo.service.AgendamentoService;
import br.com.unilab.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AgendamentoController {


    private final UsuarioService usuarioService;
    private final AgendamentoService agendamentoService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar-agendamento/{id}")
    public ResponseEntity<Object> deletarAgendamento(@PathVariable("id") Long id) {
        return agendamentoService.buscarAgendamento(id)
                .map(agendamento -> {
                    agendamentoService.deletarAgendamento(agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/atualizar-agendamento/{id}")
    public ResponseEntity<Object> atualizarAgendamento(@PathVariable("id") Long id, @RequestBody Agendamento agendamento) {
        return agendamentoService.buscarAgendamento(id)
                .map(agendamentoExistente -> {
                    agendamentoService.atualizarAgendamento(agendamentoExistente, agendamento);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/listar-agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
            if (agendamentos.isEmpty()) {
                throw new AgendamentoNaoLocalizadoException("Não possui laboratórios cadastrados!");
            }
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            throw new RuntimeException("Erro Inesperado! Gentileza contatar o setor de suporte!");
        }
    }

    /*@PostMapping("/solicitar-agendamento")
    public String agendar(@ModelAttribute Agendamento agendamento, Authentication authentication, RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);

            agendamento.setUsuario(usuario);
            agendamentoService.solicitarAgendamento(agendamento);
            redirectAttributes.addFlashAttribute("mensagem", "Agendamento Salvo com sucesso");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar: usuário não encontrado");
        }
        return "redirect:/form-agendamento";
    }*/
}




