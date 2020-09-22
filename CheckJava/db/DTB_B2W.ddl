-- Gerado por Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   em:        2020-09-20 18:54:36 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



DROP TABLE t_sg_calendario CASCADE CONSTRAINTS;

DROP TABLE t_sg_cargo CASCADE CONSTRAINTS;

DROP TABLE t_sg_equipe CASCADE CONSTRAINTS;

DROP TABLE t_sg_medalha CASCADE CONSTRAINTS;

DROP TABLE t_sg_projeto CASCADE CONSTRAINTS;

DROP TABLE t_sg_representante CASCADE CONSTRAINTS;

DROP TABLE t_sg_sala_chat CASCADE CONSTRAINTS;

DROP TABLE t_sg_tarefa CASCADE CONSTRAINTS;

DROP TABLE t_sg_usuario CASCADE CONSTRAINTS;

CREATE TABLE t_sg_calendario (
    cd_evento  NUMBER NOT NULL,
    cd_tarefa  NUMBER NOT NULL,
    dt_evento  DATE NOT NULL,
    nm_evento  VARCHAR2(30) NOT NULL,
    ds_evento  VARCHAR2(100)
);

ALTER TABLE t_sg_calendario ADD CONSTRAINT pk_sg_calendario PRIMARY KEY ( cd_evento );

CREATE TABLE t_sg_cargo (
    cd_cargo  NUMBER NOT NULL,
    nm_cargo  VARCHAR2(50) NOT NULL
);

ALTER TABLE t_sg_cargo ADD CONSTRAINT pk_sg_cargo PRIMARY KEY ( cd_cargo );

CREATE TABLE t_sg_equipe (
    cd_equipe     NUMBER NOT NULL,
    cd_matricula  NUMBER NOT NULL,
    nm_equipe     VARCHAR2(30) NOT NULL
);

ALTER TABLE t_sg_equipe ADD CONSTRAINT pk_sg_equipe PRIMARY KEY ( cd_equipe );

CREATE TABLE t_sg_medalha (
    cd_medalha    NUMBER NOT NULL,
    cd_matricula  NUMBER NOT NULL,
    cd_projeto    NUMBER NOT NULL,
    tipo_medalha  NUMBER(1) NOT NULL,
    ds_medalha    VARCHAR2(100) NOT NULL
);

COMMENT ON COLUMN t_sg_medalha.tipo_medalha IS
    '1 = Medalha individual
2 = Medalha para Equipe inteira';

ALTER TABLE t_sg_medalha ADD CONSTRAINT pk_sg_medalha PRIMARY KEY ( cd_medalha );

CREATE TABLE t_sg_projeto (
    cd_projeto  NUMBER NOT NULL,
    cd_equipe   NUMBER NOT NULL,
    nm_projeto  VARCHAR2(30) NOT NULL
);

ALTER TABLE t_sg_projeto ADD CONSTRAINT pk_sg_projeto PRIMARY KEY ( cd_projeto );

CREATE TABLE t_sg_representante (
    cd_matricula  NUMBER NOT NULL,
    cd_tarefa     NUMBER NOT NULL
);

ALTER TABLE t_sg_representante ADD CONSTRAINT pk_sg_representante PRIMARY KEY ( cd_matricula );

CREATE TABLE t_sg_sala_chat (
    cd_sala        NUMBER NOT NULL,
    cd_equipe      NUMBER NOT NULL,
    tx_mensagens   CLOB,
    arq_mensagens  BLOB
);

CREATE UNIQUE INDEX un_sg_sala_chat ON
    t_sg_sala_chat (
        cd_equipe
    ASC );

ALTER TABLE t_sg_sala_chat ADD CONSTRAINT pk_sg_sala_chat PRIMARY KEY ( cd_sala );

CREATE TABLE t_sg_tarefa (
    cd_tarefa   NUMBER NOT NULL,
    cd_projeto  NUMBER NOT NULL,
    nm_tarefa   VARCHAR2(30) NOT NULL,
    dt_inicio   DATE,
    dt_entrega  DATE,
    st_tarefa   NUMBER(1),
    ds_tarefa   VARCHAR2(100)
);

COMMENT ON COLUMN t_sg_tarefa.st_tarefa IS
    '1 = A Fazer
2 = Feito
3 = Em progresso
4 = Parado';

ALTER TABLE t_sg_tarefa ADD CONSTRAINT pk_sg_tarefa PRIMARY KEY ( cd_tarefa );

CREATE TABLE t_sg_usuario (
    cd_matricula  NUMBER NOT NULL,
    cd_cargo      NUMBER NOT NULL,
    nm_usuario    VARCHAR2(50) NOT NULL,
    dc_cpf        VARCHAR2(11) NOT NULL,
    ed_email      VARCHAR2(60) NOT NULL,
    nr_telefone   VARCHAR2(11) NOT NULL
);

ALTER TABLE t_sg_usuario ADD CONSTRAINT pk_sg_usuario PRIMARY KEY ( cd_matricula );

ALTER TABLE t_sg_calendario
    ADD CONSTRAINT fk_sg_calendario_tarefa FOREIGN KEY ( cd_tarefa )
        REFERENCES t_sg_tarefa ( cd_tarefa );

ALTER TABLE t_sg_equipe
    ADD CONSTRAINT fk_sg_equipe_usuario FOREIGN KEY ( cd_matricula )
        REFERENCES t_sg_usuario ( cd_matricula );

ALTER TABLE t_sg_medalha
    ADD CONSTRAINT fk_sg_medalha_projeto FOREIGN KEY ( cd_projeto )
        REFERENCES t_sg_projeto ( cd_projeto );

ALTER TABLE t_sg_medalha
    ADD CONSTRAINT fk_sg_medalha_representante FOREIGN KEY ( cd_matricula )
        REFERENCES t_sg_representante ( cd_matricula );

ALTER TABLE t_sg_projeto
    ADD CONSTRAINT fk_sg_projeto_equipe FOREIGN KEY ( cd_equipe )
        REFERENCES t_sg_equipe ( cd_equipe );

ALTER TABLE t_sg_representante
    ADD CONSTRAINT fk_sg_representante_tarefa FOREIGN KEY ( cd_tarefa )
        REFERENCES t_sg_tarefa ( cd_tarefa );

ALTER TABLE t_sg_representante
    ADD CONSTRAINT fk_sg_representante_usuario FOREIGN KEY ( cd_matricula )
        REFERENCES t_sg_usuario ( cd_matricula );

ALTER TABLE t_sg_sala_chat
    ADD CONSTRAINT fk_sg_sala_chat_equipe FOREIGN KEY ( cd_equipe )
        REFERENCES t_sg_equipe ( cd_equipe );

ALTER TABLE t_sg_tarefa
    ADD CONSTRAINT fk_sg_tarefa_projeto FOREIGN KEY ( cd_projeto )
        REFERENCES t_sg_projeto ( cd_projeto );

ALTER TABLE t_sg_usuario
    ADD CONSTRAINT fk_sg_usuario_cargo FOREIGN KEY ( cd_cargo )
        REFERENCES t_sg_cargo ( cd_cargo );



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             9
-- CREATE INDEX                             1
-- ALTER TABLE                             19
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
