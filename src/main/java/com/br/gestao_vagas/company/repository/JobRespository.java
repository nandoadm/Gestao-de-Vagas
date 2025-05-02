package com.br.gestao_vagas.company.repository;

import com.br.gestao_vagas.company.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRespository extends JpaRepository<JobEntity, Integer> {

    List<JobEntity> findByDescriptionContainingIgnoreCase(String Filter);

    Optional<Object> findById(UUID idJob);
}
