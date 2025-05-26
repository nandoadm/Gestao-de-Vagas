package com.br.gestao_vagas.candidate.useCases;

import com.br.gestao_vagas.candidate.entity.ApplyJobEntity;
import com.br.gestao_vagas.candidate.repository.ApplyJobRepository;
import com.br.gestao_vagas.candidate.repository.CandidateRepository;
import com.br.gestao_vagas.company.repository.JobRespository;
import com.br.gestao_vagas.exceptions.JobNotFoundExeption;
import com.br.gestao_vagas.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    private final JobRespository jobRespository;

    private final CandidateRepository candidateRepository;

    private final ApplyJobRepository applyJobRepository;

    public ApplyJobCandidateUseCase(JobRespository jobRespository, CandidateRepository candidateRepository, ApplyJobRepository applyJobRepository) {
        this.jobRespository = jobRespository;
        this.candidateRepository = candidateRepository;
        this.applyJobRepository = applyJobRepository;
    }

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.jobRespository.findById(idJob).orElseThrow(() -> {
            throw new JobNotFoundExeption();
        });

        var applyJob = ApplyJobEntity.builder()
                .jobId(idJob)
                .candidateId(idCandidate)
                .build();

        applyJob = this.applyJobRepository.save(applyJob);
        return applyJob;


    }
}
