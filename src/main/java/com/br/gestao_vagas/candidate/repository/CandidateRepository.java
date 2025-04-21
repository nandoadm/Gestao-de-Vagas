package com.br.gestao_vagas.candidate.repository;

import com.br.gestao_vagas.candidate.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID > {
    Optional<CandidateEntity>  findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity>  findByUsername(String username);

}
