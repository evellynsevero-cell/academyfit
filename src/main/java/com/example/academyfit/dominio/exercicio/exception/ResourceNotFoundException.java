package com.example.academyfit.dominio.exercicio.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super("O id não existe");
  }
}
