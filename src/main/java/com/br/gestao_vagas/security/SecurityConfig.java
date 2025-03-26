package com.br.gestao_vagas.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //Lista com rotas públicas
    private final String[] authorities = {
            "/candidate/",
            "/company/",
            "/auth/company"
    };


    //Bean - Utilizado para sobrescrever as configurações de um método
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> {
            //authorities -> lista com rotas públicas
            auth.requestMatchers(authorities).permitAll();
            //para qualquer  outra rota é necessario authenticação
            auth.anyRequest().authenticated();
        });
        return http.build();
    }


    //Classe reponsável pela criptografia da senha do Candidato/Company
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
