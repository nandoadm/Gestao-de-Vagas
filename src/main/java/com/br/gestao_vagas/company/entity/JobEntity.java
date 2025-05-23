package com.br.gestao_vagas.company.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private String description;
    private String level;
    private String curriculum;
    private String benefits;
}
