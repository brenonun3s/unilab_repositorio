package br.com.unilab.demo.model.exceptions;


public class AgendamentoNaoLocalizadoException extends RuntimeException {
    public AgendamentoNaoLocalizadoException(String mensagem) {
        super(mensagem);
    }
}
