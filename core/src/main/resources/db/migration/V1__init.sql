-- Flyway V1: somente schema (DDL)

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

-- ITINERARIO
create table if not exists itinerario (
    ordem smallint not null,
    id_linha bigint not null,
    id_ponto_parada bigint not null,
    primary key (ordem, id_linha, id_ponto_parada),
    constraint fk_it_linha foreign key (id_linha) references linha(id),
    constraint fk_it_ponto foreign key (id_ponto_parada) references ponto_parada(id)
);

-- PONTO_PARADA_TURISTICO (única tabela de relação)
create table if not exists ponto_parada_turistico (
    id_ponto_parada bigint not null,
    id_ponto_turistico bigint not null,
    primary key (id_ponto_parada, id_ponto_turistico),
    constraint fk_ppt_parada foreign key (id_ponto_parada) references ponto_parada(id),
    constraint fk_ppt_turistico foreign key (id_ponto_turistico) references ponto_turistico(id)
);

-- TIPO_VEICULO
create table if not exists tipo_veiculo (
    id bigserial primary key,
    descricao varchar(255) not null unique,
    ativo boolean not null default true,
    version integer not null default 0
);

-- VEICULO
create table if not exists veiculo (
    id bigserial primary key,
    placa varchar(50) not null unique,
    chassi varchar(100) unique,
    modelo varchar(100),
    ano_fabricacao integer not null,
    capacidade integer not null,
    ativo boolean not null default true,
    tipo_veiculo_id bigint not null,
    version integer not null default 0,
    constraint fk_veiculo_tipo foreign key (tipo_veiculo_id) references tipo_veiculo(id)
);

-- PESSOA
create table if not exists pessoa (
    id bigserial primary key,
    cpf varchar(14) not null unique,
    nome varchar(100) not null,
    email varchar(100),
    telefone varchar(20),
    data_nascimento timestamp,
    ativo boolean not null default true,
    tipo_pessoa varchar(100),
    cnh varchar(11) unique,
    validade_cnh timestamp,
    categoria_cnh varchar(10),
    numero_carteirinha varchar(20) unique,
    created_at timestamp not null,
    updated_at timestamp,
    version integer not null default 0
);

-- CRONOGRAMA
create table if not exists cronograma (
    id_cronograma bigserial primary key,
    id_linha bigint not null unique,
    hora_partida time,
    tipo_dia smallint,
    constraint fk_cronograma_linha foreign key (id_linha) references linha(id)
);

-- METODO_PAGAMENTO
create table if not exists metodo_pagamento (
    id_metodo_pagamento bigserial primary key,
    descricao varchar(255)
);

-- TIPO_PASSAGEM
create table if not exists tipo_passagem (
    id_tipo_passagem bigserial primary key,
    descricao varchar(255)
);

-- VIAGEM
create table if not exists viagem (
    id bigserial primary key,
    data_partida_real timestamp,
    data_partida_prevista timestamp not null,
    data_chegada_prevista timestamp not null,
    data_chegada_real timestamp,
    linha_id bigint not null,
    veiculo_id bigint not null,
    motorista_id bigint not null,
    status integer,
    version integer not null default 0,
    constraint fk_viagem_linha foreign key (linha_id) references linha(id),
    constraint fk_viagem_veiculo foreign key (veiculo_id) references veiculo(id),
    constraint fk_viagem_motorista foreign key (motorista_id) references pessoa(id)
);

-- PASSAGEM
create table if not exists passagem (
    id bigserial primary key,
    data_emissao timestamp not null,
    valor numeric(10,2) not null,
    desconto_aplicado numeric(10,2),
    ativo boolean not null default true,
    numero_assento smallint not null,
    pessoa_id bigint not null,
    viagem_id bigint not null,
    tipo_passagem_id bigint not null,
    metodo_pagamento_id bigint not null,
    constraint fk_passagem_pessoa foreign key (pessoa_id) references pessoa(id),
    constraint fk_passagem_viagem foreign key (viagem_id) references viagem(id),
    constraint fk_passagem_tipo foreign key (tipo_passagem_id) references tipo_passagem(id_tipo_passagem),
    constraint fk_passagem_metodo foreign key (metodo_pagamento_id) references metodo_pagamento(id_metodo_pagamento)
);

-- PECA
create table if not exists peca (
    id_peca bigserial primary key,
    valor_unitario numeric(10,2),
    nome varchar(255),
    fabricante varchar(255),
    quantidade integer
);

-- MANUTENCAO
create table if not exists manutencao (
    id_manutencao bigserial primary key,
    id_veiculo bigint not null,
    id_pessoa bigint not null,
    descricao varchar(255),
    custo_total numeric(10,2),
    data_inicio timestamp,
    data_fim timestamp,
    constraint fk_manutencao_veiculo foreign key (id_veiculo) references veiculo(id),
    constraint fk_manutencao_pessoa foreign key (id_pessoa) references pessoa(id)
);

-- MANUTENCAO_PECA
create table if not exists manutencao_peca (
    id_manutencao bigint not null,
    id_peca bigint not null,
    quantidade_utilizada integer,
    valor_unitario numeric(10,2),
    primary key (id_manutencao, id_peca),
    constraint fk_mp_manutencao foreign key (id_manutencao) references manutencao(id_manutencao),
    constraint fk_mp_peca foreign key (id_peca) references peca(id_peca)
);

-- PROGRESSO_VIAGEM
create table if not exists progresso_viagem (
    data timestamp not null,
    id_ponto_parada bigint not null,
    id_viagem bigint not null,
    primary key (data, id_ponto_parada, id_viagem),
    constraint fk_progresso_parada foreign key (id_ponto_parada) references ponto_parada(id),
    constraint fk_progresso_viagem foreign key (id_viagem) references viagem(id)
);
