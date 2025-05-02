package com.br.gestao_vagas.candidate.useCases;


import com.br.gestao_vagas.candidate.repository.CandidateRepository;
import com.br.gestao_vagas.company.repository.JobRespository;
import com.br.gestao_vagas.exceptions.JobNotFoundExeption;
import com.br.gestao_vagas.exceptions.UserNotFoundException;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks // InjectMocks -> é utilizado para Injetar na aplicação a ser testada
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock// Autoriwed
    private JobRespository jobRespository;

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try{
        applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e){
            assert e instanceof UserNotFoundException;
        }
    }
}
