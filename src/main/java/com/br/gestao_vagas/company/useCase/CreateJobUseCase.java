package com.br.gestao_vagas.company.useCase;


import com.br.gestao_vagas.company.entity.JobEntity;
import com.br.gestao_vagas.company.repository.JobRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    private final JobRespository jobRespository;

    public CreateJobUseCase(JobRespository jobRespository) {
        this.jobRespository = jobRespository;
    }

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRespository.save(jobEntity);
    }
}
