package br.com.unilab.demo.model.exceptions;

/**
 * Exceção Personalizada para Buscas sem retorno dos Agendamentos do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */

public class AgendamentoNaoLocalizadoException extends RuntimeException {
    public AgendamentoNaoLocalizadoException(String mensagem) {
        super(mensagem);
    }
}
