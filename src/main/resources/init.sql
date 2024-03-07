CREATE TABLE cliente
(
    id     SERIAL PRIMARY KEY,
    limite INTEGER NOT NULL,
    saldo  INTEGER NOT NULL
);

CREATE TABLE transacao
(
    id           SERIAL PRIMARY KEY,
    cliente_id   BIGINT                   NOT NULL,
    valor        BIGINT                   NOT NULL,
    tipo         CHAR(1)                  NOT NULL,
    descricao    VARCHAR(10)              NOT NULL,
    realizado_em TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_transacao_cliente ON transacao(cliente_id);

INSERT INTO cliente (id, limite, saldo)
VALUES (1, 100000, 0);

INSERT INTO cliente (id, limite, saldo)
VALUES (2, 80000, 0);

INSERT INTO cliente (id, limite, saldo)
VALUES (3, 1000000, 0);

INSERT INTO cliente (id, limite, saldo)
VALUES (4, 10000000, 0);

INSERT INTO cliente (id, limite, saldo)
VALUES (5, 500000, 0);