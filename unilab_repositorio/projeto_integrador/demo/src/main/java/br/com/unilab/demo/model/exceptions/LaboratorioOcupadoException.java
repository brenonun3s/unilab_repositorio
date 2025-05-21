package br.com.unilab.demo.model.exceptions;

/**
 * Exceção Personalizada para Laboratorios Ocupados do Sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 20/03/2025
 */


public class LaboratorioOcupadoException extends IllegalArgumentException {

    public LaboratorioOcupadoException(String mensagem) {
        super(mensagem);

    }


}