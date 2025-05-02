package com.br.gestao_vagas.exceptions;

public class JobNotFoundExeption  extends RuntimeException {
    public JobNotFoundExeption() {
        super("Job Not Found");
    }
}
