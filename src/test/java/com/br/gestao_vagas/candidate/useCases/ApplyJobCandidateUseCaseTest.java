package com.br.gestao_vagas.candidate.useCases;


import com.br.gestao_vagas.candidate.repository.CandidateRepository;
import com.br.gestao_vagas.company.repository.JobRespository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private JobRespository jobRespository;

    @Mock
    private CandidateRepository candidateRepository;

    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        applyJobCandidateUseCase.execute(null, null);
    }
}
