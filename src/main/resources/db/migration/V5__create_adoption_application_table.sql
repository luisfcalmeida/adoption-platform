CREATE TABLE adoption_application
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    animal_id           BIGINT NOT NULL CONSTRAINT fk_application_animal REFERENCES animal (id),
    applicant_name      VARCHAR(100) NOT NULL,
    applicant_email     VARCHAR(254) NOT NULL,
    applicant_phone     VARCHAR(20),
    message             TEXT,
    status              VARCHAR(8) NOT NULL DEFAULT 'PENDING' CONSTRAINT status_check CHECK(status in ('PENDING', 'APPROVED', 'REJECTED')),
    created_at          TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at          TIMESTAMPTZ DEFAULT now() NOT NULL
);






