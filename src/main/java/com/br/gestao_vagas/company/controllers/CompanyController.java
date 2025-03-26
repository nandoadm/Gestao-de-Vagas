package com.br.gestao_vagas.company.controllers;


import com.br.gestao_vagas.company.entity.CompanyEntity;
import com.br.gestao_vagas.company.useCase.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/")
    //Valid - verifica as requisições do vindas da CompanyEntity
    //RequestBody - Envia as requisições via JSON
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        //Tratamento de erro
        try {
            //Chamada o metodo execute para salvar a company
            var result = this.createCompanyUseCase.execute(companyEntity);
            //retorna 200
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
//            retorna erro 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
