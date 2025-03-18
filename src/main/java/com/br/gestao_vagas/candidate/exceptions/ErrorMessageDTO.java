package com.br.gestao_vagas.candidate.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    /*
        Os campos message e field vão pegar as mensagens e campos
        que retornam os erros
     */
    private String message;
    private String field;
}
