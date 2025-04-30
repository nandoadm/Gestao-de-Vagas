package com.br.gestao_vagas.company.repository;

import com.br.gestao_vagas.company.entity.JobEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRespository extends JpaRepository<JobEntity, Integer> {

        List<JobEntity> findByDescriptionContainingIgnoreCase(String Filter);
}
