CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS app.usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    endereco VARCHAR(150) NOT NULL,
    login VARCHAR(80) NOT NULL,
    cpf CHAR(11) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(30) NOT NULL,
    data_ultima_alteracao TIMESTAMP NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE UNIQUE INDEX IF NOT EXISTS ux_app_usuario_email ON app.usuario (email);
CREATE UNIQUE INDEX IF NOT EXISTS ux_app_usuario_login ON app.usuario (login);
CREATE UNIQUE INDEX IF NOT EXISTS ux_app_usuario_cpf ON app.usuario (cpf);