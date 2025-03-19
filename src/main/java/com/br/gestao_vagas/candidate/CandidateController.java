package com.br.gestao_vagas.candidate;

import com.br.gestao_vagas.exceptions.UserFoundException;
import jakarta.validation.Valid;
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

    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        //Utiliza o JPA para fazer a busca de (username, email)
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                //Faz a verificação e lança uma exceção
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        // Salva na Db(Candidate)
        return this.candidateRepository.save(candidateEntity);
    }
}
