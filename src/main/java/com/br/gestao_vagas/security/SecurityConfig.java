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
    private static final String[] authorities = {
            "/candidate/",
            "/company/",
            "/company/auth",
            "/candidate/auth",
    };

    private static final String[] SWAGGER_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**"
    };

    private static final String[] actuator = {
            "/actuator/**"
    };

    private final SecurityCompanyFilter securityCompanyFilter;

    private final SecurityCandidateFilter securityCandidateFilter;

    public SecurityConfig(SecurityCompanyFilter securityCompanyFilter, SecurityCandidateFilter securityCandidateFilter) {
        this.securityCompanyFilter = securityCompanyFilter;
        this.securityCandidateFilter = securityCandidateFilter;
    }

    //Bean - Utilizado para sobrescrever as configurações de um método
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    //authorities -> lista com rotas públicas
                    auth.requestMatchers(authorities).permitAll()
                            .requestMatchers(SWAGGER_LIST).permitAll()
                            .requestMatchers(actuator).permitAll()
                            .anyRequest().authenticated();
                    //para qualquer  outra rota é necessario authenticação
                })

                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class);

        return http.build();
    }


    //Classe reponsável pela criptografia da senha do Candidato/Company
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
