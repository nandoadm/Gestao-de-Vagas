package com.br.gestao_vagas.candidate.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    private UUID id;
    private String name;
    @Schema(example = "Naando")
    private String username;
    @Schema(example = "<EMAIL>")
    private String email;
    @Schema(example = "Full Stack Developer")
    private String description;
}
