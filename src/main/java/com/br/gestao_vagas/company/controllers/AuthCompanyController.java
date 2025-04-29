package com.br.gestao_vagas.company.controllers;


import com.br.gestao_vagas.company.dto.AuthCompanyDTO;
import com.br.gestao_vagas.company.useCase.AuthCompanyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;

    public AuthCompanyController(AuthCompanyUseCase authCompanyUseCase) {
        this.authCompanyUseCase = authCompanyUseCase;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {

        try {
            var result = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            //Erro 401 -> Usuario não tem permissão para acessar a rota
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
