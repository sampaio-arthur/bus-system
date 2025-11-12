-- Flyway V3: allow multiple cronogramas per linha and enforce not-null tarifa

-- Remove unique constraint on cronograma.id_linha (PostgreSQL default name)
alter table if exists cronograma
    drop constraint if exists cronograma_id_linha_key;

-- Ensure existing rows have a tarifa, then enforce NOT NULL
update linha set tarifa = 0.00 where tarifa is null;
alter table if exists linha
    alter column tarifa set not null;

