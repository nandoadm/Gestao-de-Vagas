package com.br.gestao_vagas.candidate.entity;


import com.br.gestao_vagas.company.entity.JobEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_jobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private CandidateEntity candidateEntity;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity jobEntity;

    @Column(name = "job_id")
    private UUID jobId;

    @Column(name = "candidate_id")
    private UUID candidateId;

    @CreationTimestamp
    private LocalDateTime createdDate;

}
