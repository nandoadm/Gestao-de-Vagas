package com.br.gestao_vagas.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
    @Schema(example = "GymPass, plano de saude", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Junior, Pleno, Senior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
    @Schema(example = "Vaga para pessoa desenvolvedora junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
