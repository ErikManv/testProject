--liquibase formatted sql
--changeset erik:1
--comment first migration

CREATE TABLE wallet
(
    ID   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    balance BIGINT
);

INSERT INTO wallet
VALUES (DEFAULT, 6000)

--rollback truncate table wallet;