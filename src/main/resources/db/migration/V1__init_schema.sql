CREATE TABLE staff_account
(
    id            BIGINT IDENTITY(1,1) PRIMARY KEY,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    active        BIT          NOT NULL DEFAULT 1,
    created_at    DATETIME2    NOT NULL DEFAULT SYSUTCDATETIME()
);

CREATE TABLE animal
(
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,
    name            NVARCHAR(255) NOT NULL,
    species         VARCHAR(255) NOT NULL CHECK (species = 'DOG' OR species = 'CAT' or species = 'OTHER'),
    breed           NVARCHAR(255) NOT NULL,
    sex             VARCHAR(255) NOT NULL CHECK (sex = 'MALE' OR sex = 'FEMALE'),
    size            VARCHAR(255) NOT NULL,
    age_years       INT          NOT NULL,
    description     NVARCHAR(1000),
    adoption_status VARCHAR(255) NOT NULL DEFAULT 'AVAILABLE',
    created_at      DATETIME2    NOT NULL DEFAULT SYSUTCDATETIME()
);

CREATE TABLE animal_photo
(
    id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    animal_id   BIGINT       NOT NULL,
    file_name   VARCHAR(255) NOT NULL,
    uploaded_at DATETIME2    NOT NULL DEFAULT SYSUTCDATETIME(),
    CONSTRAINT FK_animal_photo_animal FOREIGN KEY (animal_id) REFERENCES animal (id)
);

CREATE TABLE adoption_application
(
    id                BIGINT IDENTITY(1,1) PRIMARY KEY,
    animal_id         BIGINT       NOT NULL,
    applicant_name    NVARCHAR(255) NOT NULL,
    applicant_email   NVARCHAR(255) NOT NULL,
    applicant_phone   NVARCHAR(255) NOT NULL,
    applicant_address NVARCHAR(255) NOT NULL,
    message           NVARCHAR(1000),
    status            VARCHAR(255) NOT NULL DEFAULT 'PENDING',
    submitted_at      DATETIME2    NOT NULL DEFAULT SYSUTCDATETIME(),
    CONSTRAINT FK_adoption_application_animal FOREIGN KEY (animal_id) REFERENCES animal (id)
);

CREATE INDEX IX_animal_photo_animal_id ON animal_photo (animal_id);
CREATE INDEX IX_adoption_application_animal_id ON adoption_application (animal_id);