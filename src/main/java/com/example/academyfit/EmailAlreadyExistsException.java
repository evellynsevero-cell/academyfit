package com.example.academyfit;

public class EmailAlreadyExistsException extends Throwable {
    public EmailAlreadyExistsException() {
        super("E-mail já cadastrado!");
    }
}
