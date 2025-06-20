package com.br.gestao_vagas.company.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Email Invalid")
    private String email;

    @NotBlank
    private String username;
    //Este regex causa erro de transição, foi preciso remover para fazer a codificação do password
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
//            message = "password must be min 4 and max 12 length containing at-least 1 uppercase," +
//                    " 1 lowercase, 1 special character and 1 digit ")

    private String password;

    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
