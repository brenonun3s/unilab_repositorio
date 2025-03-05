package br.com.unilab.demo.service;

import br.com.unilab.demo.model.support.SolicitacoesSuporte;
import org.springframework.stereotype.Service;

@Service
public class AudioVisualService {


    /* O QUE EU PENSEI PARA A LOGICA {
        -> DEIXAR UM METODO AQUI NO AUDIOVISUAL, COM NOME DO REMETENTE,
        DESTINATARIO (AUDIOVISUAL) E CONTEUDO DA MENSAGEM
     */

    //TODO: MELHORAR !!!
    public SolicitacoesSuporte contatarAudioVisual(SolicitacoesSuporte chamado){
        return new SolicitacoesSuporte();
    }

}
