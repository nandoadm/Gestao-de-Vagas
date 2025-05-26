package com.br.gestao_vagas.company.useCase;


import com.br.gestao_vagas.company.entity.JobEntity;
import com.br.gestao_vagas.company.repository.CompanyRepository;
import com.br.gestao_vagas.company.repository.JobRespository;
import com.br.gestao_vagas.exceptions.CompanyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    private final JobRespository jobRespository;

    private final CompanyRepository companyRepository;

    public CreateJobUseCase(JobRespository jobRespository, CompanyRepository companyRepository) {
        this.jobRespository = jobRespository;
        this.companyRepository = companyRepository;
    }

    public JobEntity execute(JobEntity jobEntity) {
    companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
        throw new CompanyNotFoundException();
    });

        return this.jobRespository.save(jobEntity);
    }
}
