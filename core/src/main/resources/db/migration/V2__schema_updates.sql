-- Flyway V2: schema updates for new requirements

-- Add new column to tipo_passagem
alter table if exists tipo_passagem
    add column if not exists porcentagem_desconto numeric(5,2);

-- Add new column to linha
alter table if exists linha
    add column if not exists tarifa numeric(10,2);

-- Remove columns from passagem
alter table if exists passagem
    drop column if exists numero_assento;

alter table if exists passagem
    drop column if exists desconto_aplicado;

