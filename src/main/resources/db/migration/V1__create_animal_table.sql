CREATE TABLE animal (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    photo_url TEXT,
    species VARCHAR(5) NOT NULL CONSTRAINT species_check CHECK(species in ('DOG', 'CAT', 'OTHER')),
    size VARCHAR(6) CONSTRAINT size_check CHECK(size in ('SMALL', 'MEDIUM', 'LARGE', 'GIANT')),
    sex VARCHAR(6) NOT NULL CONSTRAINT sex_check CHECK(sex in ('MALE', 'FEMALE')),
    adoption_status VARCHAR(9) NOT NULL DEFAULT 'AVAILABLE' CONSTRAINT adoption_status_check CHECK(adoption_status in ('ADOPTED', 'AVAILABLE')),
    birth_date DATE,
    created_at TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT now() NOT NULL
);