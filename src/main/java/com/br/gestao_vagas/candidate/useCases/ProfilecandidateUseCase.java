package com.br.gestao_vagas.candidate.useCases;


import com.br.gestao_vagas.candidate.dto.ProfileCandidateResponseDTO;
import com.br.gestao_vagas.candidate.repository.CandidateRepository;
import com.br.gestao_vagas.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfilecandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                        });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .id(candidate.getId())
                .description(candidate.getDescription())
                .build();

        return candidateDTO;


    }
}
