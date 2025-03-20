package com.br.gestao_vagas.company.useCase;

import com.br.gestao_vagas.company.repository.CompanyRepository;
import com.br.gestao_vagas.company.entity.CompanyEntity;
import com.br.gestao_vagas.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        return companyRepository.save(companyEntity);
    }
}
