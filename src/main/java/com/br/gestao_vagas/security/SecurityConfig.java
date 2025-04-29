package com.br.gestao_vagas.security;


import com.br.gestao_vagas.candidate.security.SecurityCandidateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    //Lista com rotas públicas
    private final String[] authorities = {
            "/candidate/",
            "/company/",
            "/company/auth",
            "/candidate/auth",
    };
    private final SecurityFilter securityFilter;

    private final SecurityCandidateFilter securityCandidateFilter;

    public SecurityConfig(SecurityFilter securityFilter, SecurityCandidateFilter securityCandidateFilter) {
        this.securityFilter = securityFilter;
        this.securityCandidateFilter = securityCandidateFilter;
    }

    //Bean - Utilizado para sobrescrever as configurações de um método
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    //authorities -> lista com rotas públicas
                    auth.requestMatchers(authorities).permitAll()

                            .anyRequest().authenticated();
                    //para qualquer  outra rota é necessario authenticação
                })

                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

        return http.build();
    }


    //Classe reponsável pela criptografia da senha do Candidato/Company
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
