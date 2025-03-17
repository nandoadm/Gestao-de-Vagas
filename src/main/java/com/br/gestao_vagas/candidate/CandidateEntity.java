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
    @Email(message = "Email Invalid")
    private String email;

    @NotBlank
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing at-least 1 uppercase," +
                    " 1 lowercase, 1 special character and 1 digit ")
    private String password;

    private String description;
    private String curriculum;
}
