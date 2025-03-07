package br.com.unilab.demo.model.exceptions;


public class ProfessorNaoLocalizadoException extends RuntimeException {
    public ProfessorNaoLocalizadoException(String mensagem) {
        super(mensagem);
    }
}
