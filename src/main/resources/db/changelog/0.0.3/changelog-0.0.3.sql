--liquibase formatted sql
--changeset erik:1
--comment first migration

CREATE TABLE wallet
(
    ID   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    balance BIGINT
);

INSERT INTO wallet
VALUES (DEFAULT, 7000);
INSERT INTO wallet
VALUES (DEFAULT, 7659);
INSERT INTO wallet
VALUES (DEFAULT, 9878);
INSERT INTO wallet
VALUES (DEFAULT, 9878);

--rollback truncate table wallet;