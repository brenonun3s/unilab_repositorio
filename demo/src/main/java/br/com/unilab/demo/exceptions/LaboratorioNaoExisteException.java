package br.com.unilab.demo.exceptions;

public class LaboratorioNaoExisteException extends RuntimeException {
 public LaboratorioNaoExisteException(String message){
  super(message);
 }

}
