package br.com.rafaelsouza.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){
        super("Trabalho nao encontrado");
    }
}
