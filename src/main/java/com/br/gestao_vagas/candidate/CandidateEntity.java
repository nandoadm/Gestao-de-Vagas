package com.br.gestao_vagas.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

    @NotBlank
    private String nome;

    @NotBlank
    @Email(message = "Email Invalido")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "Username must be of 6 to 12 length with no special characters")
    private String username;

    @NotBlank
    private String password;

    private String description;
    private String curriculum;
}
