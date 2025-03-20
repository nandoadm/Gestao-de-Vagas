CREATE TABLE company
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    email       VARCHAR(255),
    username    VARCHAR(255),
    password    VARCHAR(255),
    website     VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_company PRIMARY KEY (id)
);
CREATE TABLE job
(
    id          UUID NOT NULL,
    company_id  UUID,
    description VARCHAR(255),
    level       VARCHAR(255),
    curriculum  VARCHAR(255),
    benefits    VARCHAR(255),
    CONSTRAINT pk_job PRIMARY KEY (id)
);

ALTER TABLE job
    ADD CONSTRAINT FK_JOB_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE company
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;