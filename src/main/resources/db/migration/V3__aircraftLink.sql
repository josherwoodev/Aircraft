ALTER TABLE aircraft
    ADD engine_id BIGINT;

ALTER TABLE aircraft
    ADD CONSTRAINT uc_aircraft_engine UNIQUE (engine_id);

ALTER TABLE aircraft
    ADD CONSTRAINT FK_AIRCRAFT_ON_ENGINE FOREIGN KEY (engine_id) REFERENCES engine (id);