TRUNCATE TABLE progresso_viagem RESTART IDENTITY CASCADE;
TRUNCATE TABLE manutencao_peca RESTART IDENTITY CASCADE;
TRUNCATE TABLE manutencao RESTART IDENTITY CASCADE;
TRUNCATE TABLE peca RESTART IDENTITY CASCADE;
TRUNCATE TABLE passagem RESTART IDENTITY CASCADE;
TRUNCATE TABLE viagem RESTART IDENTITY CASCADE;
TRUNCATE TABLE cronograma RESTART IDENTITY CASCADE;
TRUNCATE TABLE itinerario RESTART IDENTITY CASCADE;
TRUNCATE TABLE ponto_parada_turistico RESTART IDENTITY CASCADE;
TRUNCATE TABLE ponto_parada RESTART IDENTITY CASCADE;
TRUNCATE TABLE ponto_turistico RESTART IDENTITY CASCADE;
TRUNCATE TABLE veiculo RESTART IDENTITY CASCADE;
TRUNCATE TABLE tipo_veiculo RESTART IDENTITY CASCADE;
TRUNCATE TABLE linha RESTART IDENTITY CASCADE;
TRUNCATE TABLE pessoa RESTART IDENTITY CASCADE;
TRUNCATE TABLE cidade RESTART IDENTITY CASCADE;
TRUNCATE TABLE metodo_pagamento RESTART IDENTITY CASCADE;
TRUNCATE TABLE tipo_passagem RESTART IDENTITY CASCADE;

-- DADOS ESTÁTICOS
-- CIDADES
INSERT INTO cidade (nome, uf, version) VALUES
('Araranguá', 'SC', 0),       
('Criciúma', 'SC', 0),        
('Tubarão', 'SC', 0),         
('Florianópolis', 'SC', 0),   
('Laguna', 'SC', 0);

-- TIPOS DE VEÍCULO
INSERT INTO tipo_veiculo (descricao, ativo, version) VALUES
('Micro-ônibus Urbano', true, 0),
('Ônibus Padron', true, 0),
('Ônibus Articulado', true, 0),
('Executivo Rodoviário', true, 0);

-- VEÍCULOS
INSERT INTO veiculo (placa, chassi, modelo, ano_fabricacao, capacidade, ativo, tipo_veiculo_id, version) VALUES
-- Frota Nova
('RLA1A11', '9BW12345678901234', 'Marcopolo Torino 2023', 2023, 45, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Ônibus Padron'), 0),
('RLB2B22', '9BW12345678901235', 'Marcopolo Torino 2023', 2023, 45, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Ônibus Padron'), 0),
('RLC3C33', '9BW12345678901236', 'Caio Apache Vip V', 2022, 80, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Ônibus Articulado'), 0),
('RLD4D44', '9BW12345678901237', 'Volare Fly 10', 2024, 30, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Micro-ônibus Urbano'), 0),
-- Frota Antiga
('MBC1990', '9BW00000000000001', 'Mercedes-Benz OF-1721', 2015, 40, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Ônibus Padron'), 0),
('LYS5544', '9BW00000000000002', 'Marcopolo Viale', 2014, 40, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Ônibus Padron'), 0),
('MKE1234', '9BW00000000000003', 'Busscar Urbanuss', 2012, 35, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Micro-ônibus Urbano'), 0),
('JKA9988', '9BW00000000000004', 'Scania K310', 2016, 50, true, (SELECT id FROM tipo_veiculo WHERE descricao = 'Executivo Rodoviário'), 0);

-- FUNCIONÁRIOS
INSERT INTO pessoa (cpf, nome, email, telefone, data_nascimento, ativo, tipo_pessoa, cnh, validade_cnh, categoria_cnh, created_at, version) VALUES
('111.222.333-44', 'João da Silva', 'joao.silva@transportesc.com.br', '(48) 99999-1111', '1980-05-15', true, 'MOTORISTA', '12345678901', '2026-05-15', 'AD', NOW(), 0),
('222.333.444-55', 'Pedro Ferreira', 'pedro.ferreira@transportesc.com.br', '(48) 99999-2222', '1985-08-20', true, 'MOTORISTA', '12345678902', '2025-10-10', 'AE', NOW(), 0),
('333.444.555-66', 'Mariana Santos', 'mariana.santos@transportesc.com.br', '(48) 99999-3333', '1990-01-30', true, 'MOTORISTA', '12345678903', '2027-01-30', 'AD', NOW(), 0),
('444.555.666-77', 'Roberto Machado', 'roberto.mec@transportesc.com.br', '(48) 99999-4444', '1975-12-12', true, 'MECANICO', NULL, NULL, NULL, NOW(), 0),
('555.666.777-88', 'Carlos Souza', 'carlos.mec@transportesc.com.br', '(48) 99999-5555', '1995-07-07', true, 'MECANICO', NULL, NULL, NULL, NOW(), 0);

-- PASSAGEIROS (150 Passageiros)
INSERT INTO pessoa (cpf, nome, email, telefone, data_nascimento, ativo, tipo_pessoa, created_at, version)
SELECT 
    lpad(i::text, 3, '0') || '.000.000-' || lpad((i%99)::text, 2, '0'),
    'Passageiro ' || (ARRAY['Silva', 'Santos', 'Oliveira', 'Souza', 'Pereira', 'Lima', 'Carvalho', 'Ferreira', 'Costa', 'Almeida'])[floor(random()*10)+1] || ' ' || i,
    'usuario' || i || '@email.com',
    '(48) 9' || floor(random()*100000000)::text,
    '1980-01-01'::timestamp + random() * (timestamp '2005-12-31' - timestamp '1980-01-01'),
    true,
    'PASSAGEIRO',
    NOW(),
    0
FROM generate_series(1, 150) as i;

-- LINHAS
INSERT INTO linha (nome, codigo, ativo, tempo_percurso_estimado, version, tarifa) VALUES
('Araranguá - Morro dos Conventos', 'ARA-101', true, 45, 0, 10),
('Criciúma - UNESC/Próspera', 'CRI-200', true, 50, 0, 15),      
('Florianópolis - Volta à Ilha', 'FLN-001', true, 120, 0, 8.50),     
('Interbairros Criciúma (Rio Maina)', 'CRI-205', true, 35, 0, 10),
('Tubarão - Unisul/Centro', 'TUB-300', true, 30, 0, 12);

-- PONTOS DE PARADA
INSERT INTO ponto_parada (nome, latitude, longitude, ativo, cidade_id, version) VALUES
-- Araranguá
('Terminal Urbano Araranguá', -28.935655, -49.492043, true, (SELECT id FROM cidade WHERE nome='Araranguá'), 0),
('UFSC Araranguá - Mato Alto', -28.945000, -49.470000, true, (SELECT id FROM cidade WHERE nome='Araranguá'), 0),
('Hospital Regional Araranguá', -28.940000, -49.480000, true, (SELECT id FROM cidade WHERE nome='Araranguá'), 0),
('Praia Morro dos Conventos', -28.936400, -49.363800, true, (SELECT id FROM cidade WHERE nome='Araranguá'), 0),

-- Criciúma
('Terminal Central Criciúma', -28.678363, -49.370375, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),
('Hospital São José', -28.680000, -49.372000, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),
('Parque das Nações', -28.690000, -49.390000, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),
('Shopping Nações', -28.685000, -49.360000, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),
('UNESC Campus', -28.703286, -49.406602, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),
('Terminal Rio Maina', -28.660000, -49.420000, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),
('SATC', -28.695000, -49.400000, true, (SELECT id FROM cidade WHERE nome='Criciúma'), 0),

-- Florianópolis
('TICEN - Terminal Centro', -27.596890, -48.556440, true, (SELECT id FROM cidade WHERE nome='Florianópolis'), 0),
('Rodoviária Rita Maria', -27.594000, -48.558000, true, (SELECT id FROM cidade WHERE nome='Florianópolis'), 0),
('Beira Mar Norte - Shopping', -27.585000, -48.545000, true, (SELECT id FROM cidade WHERE nome='Florianópolis'), 0),
('UFSC Trindade', -27.601450, -48.519280, true, (SELECT id FROM cidade WHERE nome='Florianópolis'), 0),
('Lagoa da Conceição - Centrinho', -27.605000, -48.460000, true, (SELECT id FROM cidade WHERE nome='Florianópolis'), 0),
('Praia da Joaquina', -27.630000, -48.445000, true, (SELECT id FROM cidade WHERE nome='Florianópolis'), 0),

-- Tubarão
('Terminal Tubarão', -28.470000, -49.010000, true, (SELECT id FROM cidade WHERE nome='Tubarão'), 0),
('Farol Shopping', -28.475000, -49.020000, true, (SELECT id FROM cidade WHERE nome='Tubarão'), 0),
('Unisul Tubarão', -28.485000, -49.015000, true, (SELECT id FROM cidade WHERE nome='Tubarão'), 0);


-- PONTOS TURÍSTICOS
INSERT INTO ponto_turistico (nome, descricao, latitude, longitude, ativo, version) VALUES
('Morro dos Conventos', 'Falésias e dunas com vista para o mar.', -28.936400, -49.363800, true, 0),
('Mina de Visitação Octávio Fontana', 'Mina de carvão turística subterrânea.', -28.658500, -49.359000, true, 0),
('Ponte Hercílio Luz', 'Cartão postal histórico de Floripa.', -27.593500, -48.564500, true, 0),
('Parque das Nações Cincinato Naspolini', 'Parque urbano com réplica de trem.', -28.690000, -49.390000, true, 0),
('Lagoa da Conceição', 'Lagoa famosa por esportes náuticos e gastronomia.', -27.605000, -48.460000, true, 0);

-- VINCULO PONTO PARADA x TURISTICO
INSERT INTO ponto_parada_turistico (id_ponto_parada, id_ponto_turistico) VALUES
((SELECT id FROM ponto_parada WHERE nome='Praia Morro dos Conventos'), (SELECT id FROM ponto_turistico WHERE nome='Morro dos Conventos')),
((SELECT id FROM ponto_parada WHERE nome='TICEN - Terminal Centro'), (SELECT id FROM ponto_turistico WHERE nome='Ponte Hercílio Luz')),
((SELECT id FROM ponto_parada WHERE nome='Parque das Nações'), (SELECT id FROM ponto_turistico WHERE nome='Parque das Nações Cincinato Naspolini')),
((SELECT id FROM ponto_parada WHERE nome='Terminal Central Criciúma'), (SELECT id FROM ponto_turistico WHERE nome='Mina de Visitação Octávio Fontana')),
((SELECT id FROM ponto_parada WHERE nome='Lagoa da Conceição - Centrinho'), (SELECT id FROM ponto_turistico WHERE nome='Lagoa da Conceição'));

-- ITINERÁRIOS (MAIS PONTOS POR LINHA)
INSERT INTO itinerario (ordem, id_linha, id_ponto_parada) VALUES
-- ARA-101 (Araranguá - Morro)
(1, (SELECT id FROM linha WHERE codigo='ARA-101'), (SELECT id FROM ponto_parada WHERE nome='Terminal Urbano Araranguá')),
(2, (SELECT id FROM linha WHERE codigo='ARA-101'), (SELECT id FROM ponto_parada WHERE nome='Hospital Regional Araranguá')),
(3, (SELECT id FROM linha WHERE codigo='ARA-101'), (SELECT id FROM ponto_parada WHERE nome='UFSC Araranguá - Mato Alto')),
(4, (SELECT id FROM linha WHERE codigo='ARA-101'), (SELECT id FROM ponto_parada WHERE nome='Praia Morro dos Conventos')),

-- CRI-200 (Criciúma - UNESC)
(1, (SELECT id FROM linha WHERE codigo='CRI-200'), (SELECT id FROM ponto_parada WHERE nome='Terminal Central Criciúma')),
(2, (SELECT id FROM linha WHERE codigo='CRI-200'), (SELECT id FROM ponto_parada WHERE nome='Hospital São José')),
(3, (SELECT id FROM linha WHERE codigo='CRI-200'), (SELECT id FROM ponto_parada WHERE nome='Shopping Nações')),
(4, (SELECT id FROM linha WHERE codigo='CRI-200'), (SELECT id FROM ponto_parada WHERE nome='Parque das Nações')),
(5, (SELECT id FROM linha WHERE codigo='CRI-200'), (SELECT id FROM ponto_parada WHERE nome='UNESC Campus')),

-- FLN-001 (Floripa - Volta à Ilha Parcial)
(1, (SELECT id FROM linha WHERE codigo='FLN-001'), (SELECT id FROM ponto_parada WHERE nome='Rodoviária Rita Maria')),
(2, (SELECT id FROM linha WHERE codigo='FLN-001'), (SELECT id FROM ponto_parada WHERE nome='TICEN - Terminal Centro')),
(3, (SELECT id FROM linha WHERE codigo='FLN-001'), (SELECT id FROM ponto_parada WHERE nome='Beira Mar Norte - Shopping')),
(4, (SELECT id FROM linha WHERE codigo='FLN-001'), (SELECT id FROM ponto_parada WHERE nome='UFSC Trindade')),
(5, (SELECT id FROM linha WHERE codigo='FLN-001'), (SELECT id FROM ponto_parada WHERE nome='Lagoa da Conceição - Centrinho')),
(6, (SELECT id FROM linha WHERE codigo='FLN-001'), (SELECT id FROM ponto_parada WHERE nome='Praia da Joaquina')),

-- CRI-205 (Rio Maina)
(1, (SELECT id FROM linha WHERE codigo='CRI-205'), (SELECT id FROM ponto_parada WHERE nome='Terminal Central Criciúma')),
(2, (SELECT id FROM linha WHERE codigo='CRI-205'), (SELECT id FROM ponto_parada WHERE nome='SATC')),
(3, (SELECT id FROM linha WHERE codigo='CRI-205'), (SELECT id FROM ponto_parada WHERE nome='Terminal Rio Maina')),

-- TUB-300 (Tubarão)
(1, (SELECT id FROM linha WHERE codigo='TUB-300'), (SELECT id FROM ponto_parada WHERE nome='Terminal Tubarão')),
(2, (SELECT id FROM linha WHERE codigo='TUB-300'), (SELECT id FROM ponto_parada WHERE nome='Farol Shopping')),
(3, (SELECT id FROM linha WHERE codigo='TUB-300'), (SELECT id FROM ponto_parada WHERE nome='Unisul Tubarão'));

-- FINANCEIRO
INSERT INTO metodo_pagamento (descricao) VALUES
('Dinheiro'), ('Cartão Cidadão'), ('Cartão Estudante'), ('PIX'), ('Vale Transporte');

-- TIPO DE PASSAGEM COM DESCONTO
INSERT INTO tipo_passagem (descricao, porcentagem_desconto) VALUES
('Inteira', 0.00),
('Estudante', 50.00),
('Idoso', 100.00),
('PNE', 100.00),
('Professor', 25.00);

-- PEÇAS PARA MANUTENÇÃO
INSERT INTO peca (nome, fabricante, valor_unitario, quantidade) VALUES
('Pneu 275/80R22.5', 'Michelin', 2800.00, 20),
('Filtro de Óleo', 'Tecfil', 85.90, 50),
('Pastilha de Freio', 'Fras-le', 450.00, 30),
('Bateria 150Ah', 'Moura', 1200.00, 10),
('Turbina', 'Garrett', 4500.00, 3),
('Alternador 24V', 'Bosch', 1800.00, 5),
('Correia Dentada', 'Continental', 120.00, 40);


-- Inserir viagem podemos alterar a quantidade
INSERT INTO viagem (data_partida_real, data_partida_prevista, data_chegada_prevista, data_chegada_real, linha_id, veiculo_id, motorista_id, status, version)
SELECT
    d.day + h.hora + (random() * interval '10 minutes'), -- partida real
    d.day + h.hora, -- partida prevista
    d.day + h.hora + (l.tempo_percurso_estimado || ' minutes')::interval, -- chegada prev
    d.day + h.hora + (l.tempo_percurso_estimado || ' minutes')::interval + (random() * interval '15 minutes'), -- chegada real
    l.id,
    (SELECT id FROM veiculo ORDER BY random() LIMIT 1),
    (SELECT id FROM pessoa WHERE tipo_pessoa = 'MOTORISTA' ORDER BY random() LIMIT 1),
    1,
    0
FROM generate_series(CURRENT_DATE - interval '1 months', CURRENT_DATE, interval '20 day') as d(day)
CROSS JOIN (SELECT unnest(ARRAY['07:00:00', '11:30:00', '17:30:00']::time[]) as hora) as h
CROSS JOIN linha l
WHERE
    -- Regra Fim de Semana menos
    NOT (EXTRACT(DOW FROM d.day) = 0 AND h.hora NOT IN ('11:30:00'))
    AND
    NOT (EXTRACT(DOW FROM d.day) = 6 AND h.hora IN ('07:00:00'));

-- INSERIR PASSAGENS COM DIVERSIDADE DE OCUPAÇÃO POR LINHA E VIAGEM
INSERT INTO passagem (data_emissao, valor, ativo, pessoa_id, viagem_id, tipo_passagem_id, metodo_pagamento_id)
SELECT
    v.data_partida_prevista - (random() * interval '45 minutes'), -- data emissao
    -- Cálculo Valor Final
    ROUND(
        (l.tarifa * (1 - (tp.porcentagem_desconto / 100.0)))::numeric, 
        2
    ),
    true,
    (floor(random() * 150) + 6)::bigint,
    v.id,
    (floor(random() * 5)+1)::bigint,
    (SELECT id_metodo_pagamento FROM metodo_pagamento ORDER BY random() LIMIT 1)
FROM viagem v
JOIN linha l ON v.linha_id = l.id
JOIN veiculo vec ON v.veiculo_id = vec.id
CROSS JOIN LATERAL (
    SELECT id_tipo_passagem, porcentagem_desconto FROM tipo_passagem ORDER BY random() LIMIT 1
) tp
CROSS JOIN LATERAL generate_series(1,
    LEAST(
        (vec.capacidade * 1.2)::int, -- Limite físico máximo
        (CASE
            -- 1. Linha (CRI-200)
            WHEN l.codigo = 'CRI-200' AND v.data_partida_prevista::time IN ('17:30:00') THEN floor(vec.capacidade * (0.9 + random() * 0.3))
            WHEN l.codigo = 'CRI-200' AND v.data_partida_prevista::time IN ('11:30:00') THEN floor(vec.capacidade * (0.2 + random() * 0.2))
            
            -- 2. Linha (FLN-001)
            WHEN l.codigo LIKE 'FLN%' AND EXTRACT(MONTH FROM v.data_partida_prevista) IN (12, 1, 2) THEN floor(vec.capacidade * (0.9 + random() * 0.3))
            WHEN l.codigo LIKE 'FLN%' THEN floor(vec.capacidade * (0.6 + random() * 0.3))

            -- 3. Linha (ARA-101)
            WHEN l.codigo = 'ARA-101' THEN floor(vec.capacidade * (0.2 + random() * 0.4))

            -- 4. Padrão Geral
            WHEN v.data_partida_prevista::time BETWEEN '06:00:00' AND '08:00:00' OR v.data_partida_prevista::time BETWEEN '17:30:00' AND '18:30:00' THEN floor(vec.capacidade * (0.5 + random() * 0.4))
            
            -- 5. Padrão Geral
            ELSE floor(vec.capacidade * (0.05 + random() * 0.25))
        END)::int
    )
) as gs(seat);

-- 3. GERAÇÃO DE MANUTENÇÃO (ÚLTIMOS 12 MESES)
INSERT INTO manutencao (id_veiculo, id_pessoa, descricao, custo_total, data_inicio, data_fim)
SELECT 
    v.id,
    (SELECT id FROM pessoa WHERE tipo_pessoa = 'MECANICO' ORDER BY random() LIMIT 1),
    CASE 
        WHEN v.ano_fabricacao < 2018 THEN 'Manutenção Corretiva Sistema de Freios' 
        ELSE 'Revisão Periódica (10.000km)' 
    END,
    CASE 
        WHEN v.ano_fabricacao < 2018 THEN random() * 3000 + 1500
        ELSE random() * 500 + 200 
    END,
    d_data,
    d_data + (random() * interval '8 hours')
FROM veiculo v
CROSS JOIN generate_series(CURRENT_DATE - interval '1 year', CURRENT_DATE, interval '3 months') as d_data -- 1 manutenção a cada 3 meses
WHERE random() < 0.8; -- 80% chance de ocorrer

-- Vincular peças
WITH manutencao_random_peca AS (
    SELECT 
        id_manutencao,
        -- Seleciona aleatoriamente um ID do array de peças existentes
        (ARRAY[1,2,3,4,5,6,7])[floor(random() * 7 + 1)] as id_peca_selecionada
    FROM manutencao
)
INSERT INTO manutencao_peca (id_manutencao, id_peca, quantidade_utilizada, valor_unitario)
SELECT 
    mrp.id_manutencao,
    p.id_peca,
    floor(random() * 2) + 1,
    p.valor_unitario
FROM manutencao_random_peca mrp
JOIN peca p ON p.id_peca = mrp.id_peca_selecionada;