package com.br.gestao_vagas.candidate;

import com.br.gestao_vagas.candidate.useCases.CreateCandidateUseCase;
import com.br.gestao_vagas.exceptions.UserFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
RequestBody é utilizado para enviar requisições
Valid é utilizado para validação. Ex: Validar Regex do CandidateEntity
*/


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    //Instance an object CreateCandidateUseCase
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try {
            //Faz a chamada do metodo execute da classe CreateCandidateUseCase
            var result = this.createCandidateUseCase.execute(candidateEntity);
            //Retorna 200 se for bem sucedido
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            //Retorna um badRequest(400) com a mensagem de erro
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
