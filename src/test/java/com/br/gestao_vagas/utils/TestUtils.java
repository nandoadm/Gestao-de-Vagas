package com.br.gestao_vagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TestUtils {


    public static String objectToJson(Object obj) throws Exception {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany){
        Algorithm algorithm = Algorithm.HMAC256("G2wqJQJZebnXc8V+PckWa8f4QhNblDxoccuZGyY4slg=");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withSubject(idCompany.toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);
        return token;
    }
}
