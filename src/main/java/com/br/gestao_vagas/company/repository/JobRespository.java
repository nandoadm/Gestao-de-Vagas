package com.br.gestao_vagas.company.repository;

import com.br.gestao_vagas.company.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRespository extends JpaRepository<JobEntity, Integer> {
}
