package br.com.rafaelsouza.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Usuário nao encontrado");
    }
}
