package br.com.unilab.demo.service;

import br.com.unilab.demo.entities.Agendamento;
import br.com.unilab.demo.entities.Laboratorio;
import br.com.unilab.demo.entities.Usuario;
import br.com.unilab.demo.repositories.AdministradorRepository;
import br.com.unilab.demo.repositories.AgendamentoRepository;
import br.com.unilab.demo.repositories.LaboratorioRepository;
import br.com.unilab.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    AudioVisualService audioVisualService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LaboratorioRepository laboratorioRepository;

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

    public void deletarUsuario(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    public Laboratorio criarLaboratorio(Laboratorio laboratorio){
        return laboratorioRepository.save(laboratorio);
    }

    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deletarLaboratorio(Laboratorio laboratorio){
        laboratorioRepository.delete(laboratorio);
    }






}
