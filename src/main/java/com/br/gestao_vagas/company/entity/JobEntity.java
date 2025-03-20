package com.br.gestao_vagas.company.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "job")
@Data
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @Column(name = "company_id", insertable = false, updatable = false)
    private UUID companyId;

    private String description;
    private String level;
    private String curriculum;
    private String benefits;
}
