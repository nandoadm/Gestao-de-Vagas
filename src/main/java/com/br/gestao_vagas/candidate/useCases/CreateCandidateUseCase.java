package com.br.gestao_vagas.candidate.useCases;

import com.br.gestao_vagas.candidate.CandidateEntity;
import com.br.gestao_vagas.candidate.CandidateRepository;
import com.br.gestao_vagas.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
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
