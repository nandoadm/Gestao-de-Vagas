package com.br.gestao_vagas.candidate.controllers;


import com.br.gestao_vagas.candidate.dto.AuthCandidateRequestDTO;
import com.br.gestao_vagas.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    private final AuthCandidateUseCase authCandidateUseCase;

    public AuthCandidateController(AuthCandidateUseCase authCandidateUseCase) {
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try{
            var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
