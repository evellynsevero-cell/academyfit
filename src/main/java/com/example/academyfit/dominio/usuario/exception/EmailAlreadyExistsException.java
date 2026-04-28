package com.example.academyfit.dominio.usuario.exception;

public class EmailAlreadyExistsException extends Throwable {
    public EmailAlreadyExistsException() {
        super("E-mail já cadastrado!");
    }
}
