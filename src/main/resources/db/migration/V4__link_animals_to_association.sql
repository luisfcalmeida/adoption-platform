INSERT INTO association (name, email, phone, address, description)
VALUES ('Demo Rescue Association',
        'demo@example.org',
        '+351123456789',
        'Test Street 123',
        'This is a dummy association');

ALTER TABLE animal
    ADD COLUMN association_id BIGINT;

UPDATE animal
SET association_id = (SELECT id FROM association WHERE email = 'demo@example.org');

ALTER TABLE animal
    ALTER COLUMN association_id
        SET NOT NULL;

ALTER TABLE animal
    ADD CONSTRAINT fk_animal_association
        FOREIGN KEY (association_id)
            REFERENCES association (id);