-- Criação da tabela para guardar o histórico de orçamentos
CREATE TABLE IF NOT EXISTS historico_impressoes (
    id INTEGER PRIMARY KEY AUTOINCREMENT, -- Número único da impressão
    peso_gramas REAL,                     -- Peso da peça (ex: 45.5)
    tempo_horas REAL,                     -- Tempo gasto (ex: 2.5)
    valor_filamento REAL,                 -- Quanto custou só o plástico
    valor_energia REAL,                   -- Quanto custou a luz
    custo_total REAL,                     -- A soma final
    data_calculo DATETIME DEFAULT CURRENT_TIMESTAMP -- Data e hora automática
);


SELECT name FROM sqlite_master WHERE type='table';