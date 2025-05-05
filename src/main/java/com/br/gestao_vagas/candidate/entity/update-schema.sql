CREATE TABLE apply_jobs
(
    id           UUID NOT NULL,
    job_id       UUID,
    candidate_id UUID,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_apply_jobs PRIMARY KEY (id)
);

ALTER TABLE apply_jobs
    ADD CONSTRAINT FK_APPLY_JOBS_ON_CANDIDATE FOREIGN KEY (candidate_id) REFERENCES candidate (id);

ALTER TABLE apply_jobs
    ADD CONSTRAINT FK_APPLY_JOBS_ON_JOB FOREIGN KEY (job_id) REFERENCES job (id);