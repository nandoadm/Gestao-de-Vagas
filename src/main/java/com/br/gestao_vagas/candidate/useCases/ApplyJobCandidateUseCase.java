package com.br.gestao_vagas.candidate.useCases;

import com.br.gestao_vagas.candidate.repository.CandidateRepository;
import com.br.gestao_vagas.company.repository.JobRespository;
import com.br.gestao_vagas.exceptions.JobNotFoundExeption;
import com.br.gestao_vagas.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private JobRespository jobRespository;

    @Autowired
    private CandidateRepository candidateRepository;

    public void execute(UUID idCandidate, UUID idJob) {

        var candidateId = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        var JobId = this.jobRespository.findById(idJob).orElseThrow(() -> {
            throw new JobNotFoundExeption();
        });


    }
}
