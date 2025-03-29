package com.br.gestao_vagas.company.useCase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.gestao_vagas.company.dto.AuthCompanyDTO;
import com.br.gestao_vagas.company.entity.CompanyEntity;
import com.br.gestao_vagas.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    private final PasswordEncoder passwordEncoder;

    private final CompanyRepository companyRepository;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Procura a company pelo usuario
    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Company not found"));

        //Faz a verificação das senhas
        var passwordMatches = this.passwordEncoder.matches(
                authCompanyDTO.getPassword(),
                company.getPassword()
        );

        //Se não for igual -> Erro
        if (!passwordMatches) {
            throw new AuthenticationException("Password not match");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);

    }
}
