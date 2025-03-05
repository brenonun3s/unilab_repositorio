package br.com.unilab.demo.model.exceptions;


public class LaboratorioNaoLocalizadoException extends RuntimeException {
    public LaboratorioNaoLocalizadoException(String mensagem) {
        super(mensagem);
    }
}
