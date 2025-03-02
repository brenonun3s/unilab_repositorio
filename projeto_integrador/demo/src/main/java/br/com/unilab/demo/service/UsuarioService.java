package br.com.unilab.demo.service;

import br.com.unilab.demo.entities.Agendamento;
import br.com.unilab.demo.entities.AudioVisual;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import br.com.unilab.demo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AudioVisualService audioVisualService;


    public Agendamento solicitarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void cancelarAgendamento(Agendamento agendamento) {
        agendamentoRepository.delete(agendamento);
    }

    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void contatarAudioVisual(){
        audioVisualService.contatar();
    }





}
