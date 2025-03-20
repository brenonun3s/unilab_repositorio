package br.com.unilab.demo.model.exceptions;

/**
 * Exceção Personalizada para Buscas sem retorno dos Laboratorios do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */


public class LaboratorioNaoLocalizadoException extends RuntimeException {
    public LaboratorioNaoLocalizadoException(String mensagem) {
        super(mensagem);
    }
}
