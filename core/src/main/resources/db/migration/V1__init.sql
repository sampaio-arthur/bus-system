-- Flyway V1: schema inicial mínimo para entidades principais

-- CIDADE
create table if not exists cidade (
    id bigserial primary key,
    nome varchar(100) not null,
    uf char(2) not null,
    version integer not null default 0
);

-- LINHA
create table if not exists linha (
    id bigserial primary key,
    nome varchar(100) not null,
    codigo varchar(255) not null unique,
    ativo boolean not null default true,
    tempo_percurso_estimado integer null,
    version integer not null default 0
);

-- PONTO_TURISTICO
create table if not exists ponto_turistico (
    id bigserial primary key,
    nome varchar(100) not null,
    descricao varchar(500),
    latitude numeric(10,8) not null,
    longitude numeric(11,8) not null,
    ativo boolean not null default true,
    version integer not null default 0
);

-- PONTO_PARADA
create table if not exists ponto_parada (
    id bigserial primary key,
    nome varchar(100) not null,
    latitude numeric(10,8) not null,
    longitude numeric(11,8) not null,
    ativo boolean not null default true,
    cidade_id bigint not null,
    version integer not null default 0,
    constraint fk_ponto_parada_cidade foreign key (cidade_id) references cidade(id)
);

-- MANY-TO-MANY join table conforme @JoinTable
create table if not exists ponto_parada_ponto_turistico (
    ponto_parada_id bigint not null,
    ponto_turistico_id bigint not null,
    primary key (ponto_parada_id, ponto_turistico_id),
    constraint fk_pppt_parada foreign key (ponto_parada_id) references ponto_parada(id),
    constraint fk_pppt_turistico foreign key (ponto_turistico_id) references ponto_turistico(id)
);

-- ITINERARIO (chave composta: ordem, id_linha, id_ponto_parada)
create table if not exists itinerario (
    ordem smallint not null,
    id_linha bigint not null,
    id_ponto_parada bigint not null,
    primary key (ordem, id_linha, id_ponto_parada),
    constraint fk_it_linha foreign key (id_linha) references linha(id),
    constraint fk_it_ponto foreign key (id_ponto_parada) references ponto_parada(id)
);

-- PONTO_PARADA_TURISTICO (chave composta)
create table if not exists ponto_parada_turistico (
    id_ponto_parada bigint not null,
    id_ponto_turistico bigint not null,
    primary key (id_ponto_parada, id_ponto_turistico),
    constraint fk_ppt_parada foreign key (id_ponto_parada) references ponto_parada(id),
    constraint fk_ppt_turistico foreign key (id_ponto_turistico) references ponto_turistico(id)
);

