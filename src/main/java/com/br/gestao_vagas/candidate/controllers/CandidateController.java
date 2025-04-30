package com.br.gestao_vagas.candidate.controllers;

import com.br.gestao_vagas.candidate.dto.ProfileCandidateResponseDTO;
import com.br.gestao_vagas.candidate.entity.CandidateEntity;
import com.br.gestao_vagas.candidate.useCases.CreateCandidateUseCase;
import com.br.gestao_vagas.candidate.useCases.ListAllJobsByFilterUseCase;
import com.br.gestao_vagas.candidate.useCases.ProfilecandidateUseCase;
import com.br.gestao_vagas.company.entity.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
/*
RequestBody é utilizado para enviar requisições
Valid é utilizado para validação. Ex: Validar Regex do CandidateEntity
*/


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    //Instance an object CreateCandidateUseCase
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfilecandidateUseCase profilecandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try {
            //Faz a chamada do metodo execute da classe CreateCandidateUseCase
            var result = this.createCandidateUseCase.execute(candidateEntity);
            //Retorna 200 se for bem sucedido
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            //Retorna um badRequest(400) com a mensagem de erro
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/")
    @Tag(name = "Candidato", description = "Retornar candidato")
    @Operation(summary = "Perfil do candidato",
            description = "Essa funçao é responsavel por buscar as informçoes do Candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = ProfileCandidateResponseDTO.class)
                    )
            })
    })
    @ApiResponse(responseCode = "400",description = "User not found")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.profilecandidateUseCase
                    .execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            //Retorna um badRequest(400) com a mensagem de erro
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato", description = "Informações do Candidato")
    @Operation(summary = "Listagem de vagas disponivel para cadidato",
    description = "Essa função é responsavel por listar os candidatos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                        array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter){
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

}


