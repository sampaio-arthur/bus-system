-- Flyway V1: schema completo com dados iniciais para todas as entidades

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
    chassi varchar(100),
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
    constraint fk_passagem_tipo foreign key (tipo_passagem_id) references tipo_passagem(id),
    constraint fk_passagem_metodo foreign key (metodo_pagamento_id) references metodo_pagamento(id)
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

-- DADOS INICIAIS
insert into cidade (nome, uf, version) values
    ('Cidade Alpha', 'SP', 0),
    ('Cidade Beta', 'RJ', 0),
    ('Cidade Gamma', 'MG', 0),
    ('Cidade Delta', 'PR', 0),
    ('Cidade Epsilon', 'RS', 0),
    ('Cidade Zeta', 'SC', 0),
    ('Cidade Eta', 'BA', 0),
    ('Cidade Theta', 'GO', 0),
    ('Cidade Iota', 'PE', 0),
    ('Cidade Kappa', 'CE', 0);

insert into linha (nome, codigo, ativo, tempo_percurso_estimado, version) values
    ('Linha Central', 'L001', true, 45, 0),
    ('Linha Norte', 'L002', true, 50, 0),
    ('Linha Sul', 'L003', true, 40, 0),
    ('Linha Leste', 'L004', true, 55, 0),
    ('Linha Oeste', 'L005', true, 60, 0),
    ('Linha Circular A', 'L006', true, 35, 0),
    ('Linha Circular B', 'L007', true, 30, 0),
    ('Linha Aeroporto', 'L008', true, 70, 0),
    ('Linha Expresso', 'L009', true, 65, 0),
    ('Linha Noturna', 'L010', true, 80, 0);

insert into ponto_turistico (nome, descricao, latitude, longitude, ativo, version) values
    ('Museu da Cidade', 'Museu histórico local',  -23.55050000, -46.63330000, true, 0),
    ('Parque Central', 'Maior parque urbano', -22.90680000, -43.17290000, true, 0),
    ('Mirante Azul', 'Vista panorâmica', -19.91670000, -43.93450000, true, 0),
    ('Praia do Sol', 'Praia urbana movimentada', -25.42840000, -49.27330000, true, 0),
    ('Lago Cristal', 'Lago com esportes aquáticos', -30.03460000, -51.21770000, true, 0),
    ('Cascata Verde', 'Queda d''água popular', -27.59540000, -48.54800000, true, 0),
    ('Centro Histórico', 'Casario colonial preservado', -12.97180000, -38.50110000, true, 0),
    ('Caverna das Luzes', 'Formações rochosas únicas', -16.68690000, -49.26480000, true, 0),
    ('Farol do Recife', 'Farol centenário', -8.04760000, -34.87700000, true, 0),
    ('Serra Encantada', 'Trilhas ecológicas', -3.71720000, -38.54340000, true, 0);

insert into ponto_parada (nome, latitude, longitude, ativo, cidade_id, version) values
    ('Ponto Central 1', -23.55100000, -46.63200000, true, (select id from cidade where nome = 'Cidade Alpha'), 0),
    ('Ponto Central 2', -23.55250000, -46.63450000, true, (select id from cidade where nome = 'Cidade Alpha'), 0),
    ('Ponto Norte 1', -22.90750000, -43.17500000, true, (select id from cidade where nome = 'Cidade Beta'), 0),
    ('Ponto Norte 2', -22.90550000, -43.17080000, true, (select id from cidade where nome = 'Cidade Beta'), 0),
    ('Ponto Sul 1', -19.91750000, -43.93000000, true, (select id from cidade where nome = 'Cidade Gamma'), 0),
    ('Ponto Sul 2', -19.91550000, -43.93700000, true, (select id from cidade where nome = 'Cidade Gamma'), 0),
    ('Ponto Leste 1', -25.42900000, -49.27000000, true, (select id from cidade where nome = 'Cidade Delta'), 0),
    ('Ponto Oeste 1', -30.03350000, -51.21950000, true, (select id from cidade where nome = 'Cidade Epsilon'), 0),
    ('Ponto Circular A1', -27.59600000, -48.54700000, true, (select id from cidade where nome = 'Cidade Zeta'), 0),
    ('Ponto Aeroporto', -12.97250000, -38.50000000, true, (select id from cidade where nome = 'Cidade Eta'), 0);

insert into ponto_parada_ponto_turistico (ponto_parada_id, ponto_turistico_id) values
    ((select id from ponto_parada where nome = 'Ponto Central 1'), (select id from ponto_turistico where nome = 'Museu da Cidade')),
    ((select id from ponto_parada where nome = 'Ponto Central 1'), (select id from ponto_turistico where nome = 'Parque Central')),
    ((select id from ponto_parada where nome = 'Ponto Central 2'), (select id from ponto_turistico where nome = 'Museu da Cidade')),
    ((select id from ponto_parada where nome = 'Ponto Norte 1'), (select id from ponto_turistico where nome = 'Parque Central')),
    ((select id from ponto_parada where nome = 'Ponto Norte 2'), (select id from ponto_turistico where nome = 'Mirante Azul')),
    ((select id from ponto_parada where nome = 'Ponto Sul 1'), (select id from ponto_turistico where nome = 'Praia do Sol')),
    ((select id from ponto_parada where nome = 'Ponto Sul 2'), (select id from ponto_turistico where nome = 'Lago Cristal')),
    ((select id from ponto_parada where nome = 'Ponto Leste 1'), (select id from ponto_turistico where nome = 'Cascata Verde')),
    ((select id from ponto_parada where nome = 'Ponto Oeste 1'), (select id from ponto_turistico where nome = 'Centro Histórico')),
    ((select id from ponto_parada where nome = 'Ponto Aeroporto'), (select id from ponto_turistico where nome = 'Farol do Recife'));

insert into ponto_parada_turistico (id_ponto_parada, id_ponto_turistico) values
    ((select id from ponto_parada where nome = 'Ponto Central 1'), (select id from ponto_turistico where nome = 'Museu da Cidade')),
    ((select id from ponto_parada where nome = 'Ponto Central 2'), (select id from ponto_turistico where nome = 'Museu da Cidade')),
    ((select id from ponto_parada where nome = 'Ponto Norte 1'), (select id from ponto_turistico where nome = 'Parque Central')),
    ((select id from ponto_parada where nome = 'Ponto Norte 2'), (select id from ponto_turistico where nome = 'Mirante Azul')),
    ((select id from ponto_parada where nome = 'Ponto Sul 1'), (select id from ponto_turistico where nome = 'Praia do Sol')),
    ((select id from ponto_parada where nome = 'Ponto Sul 2'), (select id from ponto_turistico where nome = 'Lago Cristal')),
    ((select id from ponto_parada where nome = 'Ponto Leste 1'), (select id from ponto_turistico where nome = 'Cascata Verde')),
    ((select id from ponto_parada where nome = 'Ponto Oeste 1'), (select id from ponto_turistico where nome = 'Centro Histórico')),
    ((select id from ponto_parada where nome = 'Ponto Circular A1'), (select id from ponto_turistico where nome = 'Serra Encantada')),
    ((select id from ponto_parada where nome = 'Ponto Aeroporto'), (select id from ponto_turistico where nome = 'Farol do Recife'));

insert into itinerario (ordem, id_linha, id_ponto_parada) values
    (1, (select id from linha where codigo = 'L001'), (select id from ponto_parada where nome = 'Ponto Central 1')),
    (2, (select id from linha where codigo = 'L001'), (select id from ponto_parada where nome = 'Ponto Central 2')),
    (1, (select id from linha where codigo = 'L002'), (select id from ponto_parada where nome = 'Ponto Norte 1')),
    (2, (select id from linha where codigo = 'L002'), (select id from ponto_parada where nome = 'Ponto Norte 2')),
    (1, (select id from linha where codigo = 'L003'), (select id from ponto_parada where nome = 'Ponto Sul 1')),
    (2, (select id from linha where codigo = 'L003'), (select id from ponto_parada where nome = 'Ponto Sul 2')),
    (1, (select id from linha where codigo = 'L004'), (select id from ponto_parada where nome = 'Ponto Leste 1')),
    (1, (select id from linha where codigo = 'L005'), (select id from ponto_parada where nome = 'Ponto Oeste 1')),
    (1, (select id from linha where codigo = 'L006'), (select id from ponto_parada where nome = 'Ponto Circular A1')),
    (1, (select id from linha where codigo = 'L008'), (select id from ponto_parada where nome = 'Ponto Aeroporto'));

insert into tipo_veiculo (descricao, ativo, version) values
    ('Ônibus Urbano', true, 0),
    ('Micro-ônibus', true, 0),
    ('Ônibus Executivo', true, 0),
    ('Mini-ônibus', true, 0),
    ('Articulado', true, 0),
    ('Double Decker', true, 0),
    ('Elétrico', true, 0),
    ('Híbrido', true, 0),
    ('Intermunicipal', true, 0),
    ('Escolar', true, 0);

insert into veiculo (placa, chassi, modelo, ano_fabricacao, capacidade, ativo, tipo_veiculo_id, version) values
    ('ABC1A23', 'CHASSI0001', 'Modelo A', 2018, 45, true, (select id from tipo_veiculo where descricao = 'Ônibus Urbano'), 0),
    ('DEF4B56', 'CHASSI0002', 'Modelo B', 2019, 30, true, (select id from tipo_veiculo where descricao = 'Micro-ônibus'), 0),
    ('GHI7C89', 'CHASSI0003', 'Modelo C', 2020, 40, true, (select id from tipo_veiculo where descricao = 'Ônibus Executivo'), 0),
    ('JKL0D12', 'CHASSI0004', 'Modelo D', 2017, 28, true, (select id from tipo_veiculo where descricao = 'Mini-ônibus'), 0),
    ('MNO3E45', 'CHASSI0005', 'Modelo E', 2021, 60, true, (select id from tipo_veiculo where descricao = 'Articulado'), 0),
    ('PQR6F78', 'CHASSI0006', 'Modelo F', 2022, 65, true, (select id from tipo_veiculo where descricao = 'Double Decker'), 0),
    ('STU9G01', 'CHASSI0007', 'Modelo G', 2021, 50, true, (select id from tipo_veiculo where descricao = 'Elétrico'), 0),
    ('VWX2H34', 'CHASSI0008', 'Modelo H', 2016, 55, true, (select id from tipo_veiculo where descricao = 'Híbrido'), 0),
    ('YZA5J67', 'CHASSI0009', 'Modelo I', 2019, 48, true, (select id from tipo_veiculo where descricao = 'Intermunicipal'), 0),
    ('BCD8K90', 'CHASSI0010', 'Modelo J', 2015, 35, true, (select id from tipo_veiculo where descricao = 'Escolar'), 0);

insert into pessoa (cpf, nome, email, telefone, data_nascimento, ativo, tipo_pessoa, cnh, validade_cnh, categoria_cnh, numero_carteirinha, created_at, updated_at, version) values
    ('111.111.111-11', 'Ana Motorista', 'ana.motorista@bus.com', '(11)90000-0001', '1985-03-10 00:00:00', true, 'MOTORISTA', '12345678901', '2026-12-31 00:00:00', 'D', null, now(), now(), 0),
    ('222.222.222-22', 'Bruno Motorista', 'bruno.motorista@bus.com', '(11)90000-0002', '1982-07-15 00:00:00', true, 'MOTORISTA', '12345678902', '2025-11-30 00:00:00', 'D', null, now(), now(), 0),
    ('333.333.333-33', 'Carla Motorista', 'carla.motorista@bus.com', '(11)90000-0003', '1990-01-20 00:00:00', true, 'MOTORISTA', '12345678903', '2027-05-15 00:00:00', 'D', null, now(), now(), 0),
    ('444.444.444-44', 'Daniel Passageiro', 'daniel.passageiro@bus.com', '(11)90000-0004', '1995-09-05 00:00:00', true, 'PASSAGEIRO', null, null, null, 'P001', now(), now(), 0),
    ('555.555.555-55', 'Elisa Passageira', 'elisa.passageira@bus.com', '(11)90000-0005', '1998-02-14 00:00:00', true, 'PASSAGEIRO', null, null, null, 'P002', now(), now(), 0),
    ('666.666.666-66', 'Felipe Passageiro', 'felipe.passageiro@bus.com', '(11)90000-0006', '2000-06-25 00:00:00', true, 'PASSAGEIRO', null, null, null, 'P003', now(), now(), 0),
    ('777.777.777-77', 'Gabriela Passageira', 'gabriela.passageira@bus.com', '(11)90000-0007', '1993-04-18 00:00:00', true, 'PASSAGEIRO', null, null, null, 'P004', now(), now(), 0),
    ('888.888.888-88', 'Heitor Mecanico', 'heitor.mecanico@bus.com', '(11)90000-0008', '1980-08-12 00:00:00', true, 'MECANICO', null, null, null, null, now(), now(), 0),
    ('999.999.999-99', 'Isabela Mecanica', 'isabela.mecanica@bus.com', '(11)90000-0009', '1987-11-30 00:00:00', true, 'MECANICO', null, null, null, null, now(), now(), 0),
    ('000.000.000-00', 'João Passageiro', 'joao.passageiro@bus.com', '(11)90000-0010', '1992-12-02 00:00:00', true, 'PASSAGEIRO', null, null, null, 'P005', now(), now(), 0);

insert into cronograma (id_linha, hora_partida, tipo_dia) values
    ((select id from linha where codigo = 'L001'), '06:00:00', 1),
    ((select id from linha where codigo = 'L002'), '06:30:00', 1),
    ((select id from linha where codigo = 'L003'), '07:00:00', 1),
    ((select id from linha where codigo = 'L004'), '07:30:00', 2),
    ((select id from linha where codigo = 'L005'), '08:00:00', 2),
    ((select id from linha where codigo = 'L006'), '08:30:00', 3),
    ((select id from linha where codigo = 'L007'), '09:00:00', 3),
    ((select id from linha where codigo = 'L008'), '09:30:00', 4),
    ((select id from linha where codigo = 'L009'), '10:00:00', 4),
    ((select id from linha where codigo = 'L010'), '10:30:00', 5);

insert into metodo_pagamento (descricao) values
    ('Dinheiro'),
    ('Cartão de Crédito'),
    ('Cartão de Débito'),
    ('Vale-Transporte'),
    ('Carteira Digital'),
    ('PIX'),
    ('Boleto'),
    ('Crédito Corporativo'),
    ('QR Code'),
    ('Pagamento Antecipado');

insert into tipo_passagem (descricao) values
    ('Inteira'),
    ('Meia-Estudante'),
    ('Idoso'),
    ('Passe Mensal'),
    ('Passe Diário'),
    ('Turístico'),
    ('Executivo'),
    ('Airport Express'),
    ('Corporativo'),
    ('Evento Especial');

insert into viagem (data_partida_real, data_partida_prevista, data_chegada_prevista, data_chegada_real, linha_id, veiculo_id, motorista_id, status, version) values
    ('2024-05-01 06:05:00', '2024-05-01 06:00:00', '2024-05-01 07:00:00', '2024-05-01 07:10:00', (select id from linha where codigo = 'L001'), (select id from veiculo where placa = 'ABC1A23'), (select id from pessoa where nome = 'Ana Motorista'), 1, 0),
    ('2024-05-01 06:35:00', '2024-05-01 06:30:00', '2024-05-01 07:40:00', '2024-05-01 07:45:00', (select id from linha where codigo = 'L002'), (select id from veiculo where placa = 'DEF4B56'), (select id from pessoa where nome = 'Bruno Motorista'), 1, 0),
    ('2024-05-01 07:10:00', '2024-05-01 07:00:00', '2024-05-01 08:00:00', '2024-05-01 08:10:00', (select id from linha where codigo = 'L003'), (select id from veiculo where placa = 'GHI7C89'), (select id from pessoa where nome = 'Carla Motorista'), 1, 0),
    ('2024-05-01 07:45:00', '2024-05-01 07:30:00', '2024-05-01 08:45:00', '2024-05-01 08:50:00', (select id from linha where codigo = 'L004'), (select id from veiculo where placa = 'JKL0D12'), (select id from pessoa where nome = 'Ana Motorista'), 1, 0),
    ('2024-05-01 08:05:00', '2024-05-01 08:00:00', '2024-05-01 09:10:00', '2024-05-01 09:15:00', (select id from linha where codigo = 'L005'), (select id from veiculo where placa = 'MNO3E45'), (select id from pessoa where nome = 'Bruno Motorista'), 1, 0),
    ('2024-05-01 08:40:00', '2024-05-01 08:30:00', '2024-05-01 09:20:00', '2024-05-01 09:25:00', (select id from linha where codigo = 'L006'), (select id from veiculo where placa = 'PQR6F78'), (select id from pessoa where nome = 'Carla Motorista'), 1, 0),
    ('2024-05-01 09:05:00', '2024-05-01 09:00:00', '2024-05-01 09:50:00', '2024-05-01 09:55:00', (select id from linha where codigo = 'L007'), (select id from veiculo where placa = 'STU9G01'), (select id from pessoa where nome = 'Ana Motorista'), 1, 0),
    ('2024-05-01 09:45:00', '2024-05-01 09:30:00', '2024-05-01 11:00:00', '2024-05-01 11:05:00', (select id from linha where codigo = 'L008'), (select id from veiculo where placa = 'VWX2H34'), (select id from pessoa where nome = 'Bruno Motorista'), 1, 0),
    ('2024-05-01 10:05:00', '2024-05-01 10:00:00', '2024-05-01 11:20:00', '2024-05-01 11:25:00', (select id from linha where codigo = 'L009'), (select id from veiculo where placa = 'YZA5J67'), (select id from pessoa where nome = 'Carla Motorista'), 1, 0),
    ('2024-05-01 10:40:00', '2024-05-01 10:30:00', '2024-05-01 12:00:00', '2024-05-01 12:05:00', (select id from linha where codigo = 'L010'), (select id from veiculo where placa = 'BCD8K90'), (select id from pessoa where nome = 'Ana Motorista'), 1, 0);

insert into passagem (data_emissao, valor, desconto_aplicado, ativo, numero_assento, pessoa_id, viagem_id, tipo_passagem_id, metodo_pagamento_id) values
    ('2024-04-30 10:00:00', 7.50, null, true, 1, (select id from pessoa where nome = 'Daniel Passageiro'), (select id from viagem where linha_id = (select id from linha where codigo = 'L001')), (select id_tipo_passagem from tipo_passagem where descricao = 'Inteira'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Cartão de Crédito')),
    ('2024-04-30 10:05:00', 3.75, 3.75, true, 2, (select id from pessoa where nome = 'Elisa Passageira'), (select id from viagem where linha_id = (select id from linha where codigo = 'L001')), (select id_tipo_passagem from tipo_passagem where descricao = 'Meia-Estudante'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Carteira Digital')),
    ('2024-04-30 10:10:00', 6.00, 1.50, true, 3, (select id from pessoa where nome = 'Felipe Passageiro'), (select id from viagem where linha_id = (select id from linha where codigo = 'L002')), (select id_tipo_passagem from tipo_passagem where descricao = 'Passe Diário'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Dinheiro')),
    ('2024-04-30 10:15:00', 12.00, null, true, 4, (select id from pessoa where nome = 'Gabriela Passageira'), (select id from viagem where linha_id = (select id from linha where codigo = 'L003')), (select id_tipo_passagem from tipo_passagem where descricao = 'Executivo'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'PIX')),
    ('2024-04-30 10:20:00', 15.00, null, true, 5, (select id from pessoa where nome = 'João Passageiro'), (select id from viagem where linha_id = (select id from linha where codigo = 'L004')), (select id_tipo_passagem from tipo_passagem where descricao = 'Turístico'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Cartão de Crédito')),
    ('2024-04-30 10:25:00', 8.50, 2.00, true, 6, (select id from pessoa where nome = 'Daniel Passageiro'), (select id from viagem where linha_id = (select id from linha where codigo = 'L005')), (select id_tipo_passagem from tipo_passagem where descricao = 'Passe Mensal'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Vale-Transporte')),
    ('2024-04-30 10:30:00', 9.00, null, true, 7, (select id from pessoa where nome = 'Elisa Passageira'), (select id from viagem where linha_id = (select id from linha where codigo = 'L006')), (select id_tipo_passagem from tipo_passagem where descricao = 'Inteira'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Cartão de Débito')),
    ('2024-04-30 10:35:00', 18.00, null, true, 8, (select id from pessoa where nome = 'Felipe Passageiro'), (select id from viagem where linha_id = (select id from linha where codigo = 'L007')), (select id_tipo_passagem from tipo_passagem where descricao = 'Airport Express'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'QR Code')),
    ('2024-04-30 10:40:00', 20.00, null, true, 9, (select id from pessoa where nome = 'Gabriela Passageira'), (select id from viagem where linha_id = (select id from linha where codigo = 'L008')), (select id_tipo_passagem from tipo_passagem where descricao = 'Corporativo'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Crédito Corporativo')),
    ('2024-04-30 10:45:00', 25.00, 5.00, true, 10, (select id from pessoa where nome = 'João Passageiro'), (select id from viagem where linha_id = (select id from linha where codigo = 'L009')), (select id_tipo_passagem from tipo_passagem where descricao = 'Evento Especial'), (select id_metodo_pagamento from metodo_pagamento where descricao = 'Pagamento Antecipado'));

insert into peca (valor_unitario, nome, fabricante, quantidade) values
    (120.00, 'Filtro de Ar', 'Fabricante A', 50),
    (80.00, 'Pastilha de Freio', 'Fabricante B', 100),
    (150.00, 'Amortecedor', 'Fabricante C', 40),
    (60.00, 'Correia Dentada', 'Fabricante D', 70),
    (200.00, 'Embreagem', 'Fabricante E', 30),
    (90.00, 'Filtro de Óleo', 'Fabricante F', 120),
    (45.00, 'Lâmpada Led', 'Fabricante G', 200),
    (300.00, 'Bateria', 'Fabricante H', 25),
    (35.00, 'Palheta', 'Fabricante I', 150),
    (500.00, 'Sistema de Freio ABS', 'Fabricante J', 15);

insert into manutencao (id_veiculo, id_pessoa, descricao, custo_total, data_inicio, data_fim) values
    ((select id from veiculo where placa = 'ABC1A23'), (select id from pessoa where nome = 'Heitor Mecanico'), 'Troca de óleo e filtro', 210.00, '2024-04-20 08:00:00', '2024-04-20 12:00:00'),
    ((select id from veiculo where placa = 'DEF4B56'), (select id from pessoa where nome = 'Isabela Mecanica'), 'Revisão de freios', 320.00, '2024-04-21 09:00:00', '2024-04-21 13:00:00'),
    ((select id from veiculo where placa = 'GHI7C89'), (select id from pessoa where nome = 'Heitor Mecanico'), 'Troca de amortecedores', 600.00, '2024-04-22 10:00:00', '2024-04-22 18:00:00'),
    ((select id from veiculo where placa = 'JKL0D12'), (select id from pessoa where nome = 'Isabela Mecanica'), 'Ajuste de correia', 150.00, '2024-04-23 08:30:00', '2024-04-23 11:30:00'),
    ((select id from veiculo where placa = 'MNO3E45'), (select id from pessoa where nome = 'Heitor Mecanico'), 'Manutenção preventiva', 400.00, '2024-04-24 07:45:00', '2024-04-24 15:00:00'),
    ((select id from veiculo where placa = 'PQR6F78'), (select id from pessoa where nome = 'Isabela Mecanica'), 'Troca de bateria', 350.00, '2024-04-25 09:15:00', '2024-04-25 12:45:00'),
    ((select id from veiculo where placa = 'STU9G01'), (select id from pessoa where nome = 'Heitor Mecanico'), 'Substituição de lâmpadas', 90.00, '2024-04-26 13:00:00', '2024-04-26 16:00:00'),
    ((select id from veiculo where placa = 'VWX2H34'), (select id from pessoa where nome = 'Isabela Mecanica'), 'Troca de pastilhas', 280.00, '2024-04-27 08:00:00', '2024-04-27 12:30:00'),
    ((select id from veiculo where placa = 'YZA5J67'), (select id from pessoa where nome = 'Heitor Mecanico'), 'Revisão elétrica', 500.00, '2024-04-28 10:30:00', '2024-04-28 17:30:00'),
    ((select id from veiculo where placa = 'BCD8K90'), (select id from pessoa where nome = 'Isabela Mecanica'), 'Revisão completa', 750.00, '2024-04-29 07:30:00', '2024-04-29 19:00:00');

insert into manutencao_peca (id_manutencao, id_peca, quantidade_utilizada, valor_unitario) values
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'ABC1A23'), (select id_peca from peca where nome = 'Filtro de Óleo'), 1, 90.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'ABC1A23'), (select id_peca from peca where nome = 'Filtro de Ar'), 1, 120.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'DEF4B56'), (select id_peca from peca where nome = 'Pastilha de Freio'), 4, 80.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'GHI7C89'), (select id_peca from peca where nome = 'Amortecedor'), 4, 150.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'JKL0D12'), (select id_peca from peca where nome = 'Correia Dentada'), 1, 60.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'MNO3E45'), (select id_peca from peca where nome = 'Palheta'), 2, 35.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'PQR6F78'), (select id_peca from peca where nome = 'Bateria'), 1, 300.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'STU9G01'), (select id_peca from peca where nome = 'Lâmpada Led'), 6, 45.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'VWX2H34'), (select id_peca from peca where nome = 'Pastilha de Freio'), 4, 80.00),
    ((select id_manutencao from manutencao m join veiculo v on m.id_veiculo = v.id where v.placa = 'YZA5J67'), (select id_peca from peca where nome = 'Sistema de Freio ABS'), 1, 500.00);

insert into progresso_viagem (data, id_ponto_parada, id_viagem) values
    ('2024-05-01 06:15:00', (select id from ponto_parada where nome = 'Ponto Central 1'), (select id from viagem where linha_id = (select id from linha where codigo = 'L001'))),
    ('2024-05-01 06:50:00', (select id from ponto_parada where nome = 'Ponto Central 2'), (select id from viagem where linha_id = (select id from linha where codigo = 'L001'))),
    ('2024-05-01 07:15:00', (select id from ponto_parada where nome = 'Ponto Norte 1'), (select id from viagem where linha_id = (select id from linha where codigo = 'L002'))),
    ('2024-05-01 07:55:00', (select id from ponto_parada where nome = 'Ponto Norte 2'), (select id from viagem where linha_id = (select id from linha where codigo = 'L002'))),
    ('2024-05-01 07:20:00', (select id from ponto_parada where nome = 'Ponto Sul 1'), (select id from viagem where linha_id = (select id from linha where codigo = 'L003'))),
    ('2024-05-01 08:05:00', (select id from ponto_parada where nome = 'Ponto Sul 2'), (select id from viagem where linha_id = (select id from linha where codigo = 'L003'))),
    ('2024-05-01 08:15:00', (select id from ponto_parada where nome = 'Ponto Leste 1'), (select id from viagem where linha_id = (select id from linha where codigo = 'L004'))),
    ('2024-05-01 08:35:00', (select id from ponto_parada where nome = 'Ponto Oeste 1'), (select id from viagem where linha_id = (select id from linha where codigo = 'L005'))),
    ('2024-05-01 09:10:00', (select id from ponto_parada where nome = 'Ponto Circular A1'), (select id from viagem where linha_id = (select id from linha where codigo = 'L006'))),
    ('2024-05-01 09:55:00', (select id from ponto_parada where nome = 'Ponto Aeroporto'), (select id from viagem where linha_id = (select id from linha where codigo = 'L008')));
