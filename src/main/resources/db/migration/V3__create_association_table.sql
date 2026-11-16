CREATE TABLE association
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100)              NOT NULL,
    email       VARCHAR(254)              NOT NULL UNIQUE,
    phone       VARCHAR(20),
    address     TEXT,
    description TEXT,
    created_at  TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at  TIMESTAMPTZ DEFAULT now() NOT NULL
);