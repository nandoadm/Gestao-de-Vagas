package com.br.gestao_vagas.candidate.controllers;

import com.br.gestao_vagas.candidate.entity.CandidateEntity;
import com.br.gestao_vagas.candidate.useCases.CreateCandidateUseCase;
import com.br.gestao_vagas.candidate.useCases.ProfilecandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
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

    @Autowired
    private ProfilecandidateUseCase profilecandidateUseCase;

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

    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.profilecandidateUseCase
                    .execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            //Retorna um badRequest(400) com a mensagem de erro
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

