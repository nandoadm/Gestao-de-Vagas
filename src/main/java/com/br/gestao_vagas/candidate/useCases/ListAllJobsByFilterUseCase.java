package com.br.gestao_vagas.candidate.useCases;


import com.br.gestao_vagas.company.entity.JobEntity;
import com.br.gestao_vagas.company.repository.JobRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRespository jobRespository;

    public List<JobEntity> execute(String Filter){
        return this.jobRespository.findByDescriptionContainingIgnoreCase(Filter);
    }
}
