INSERT INTO app.usuario (
    id,
    nome,
    email,
    endereco,
    login,
    cpf,
    senha_hash,
    tipo_usuario,
    data_ultima_alteracao,
    ativo
) VALUES
-- Usuário 1: Dono de Restaurante
(
    'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
    'Chef Augusto',
    'augusto@bistro.com',
    'rua exemplo, 456',
    'augusto.admin',
    '11122233344',
    '$2a$10$ExemploDeHashBcryptGerado1234567890',
    'DONO_RESTAURANTE',
    CURRENT_TIMESTAMP,
    TRUE
),

-- Usuário 2: Cliente
(
    'b1fddc99-9c0b-4ef8-bb6d-6bb9bd380b22',
    'Mariana Silva',
    'mariana@email.com',
    'rua exemplo, 456',
    'mari.silva',
    '55566677788',
    '$2a$10$OutroExemploDeHashBcryptGerado0987654321',
    'CLIENTE',
    CURRENT_TIMESTAMP,
    TRUE
),

-- Usuário 3: Cliente (Para testar bloqueio/inativo se precisar)
(
    'c2gffe99-9c0b-4ef8-bb6d-6bb9bd380c33',
    'Roberto Carlos',
    'roberto@email.com',
    'rua exemplo, 456',
    'beto.carlos',
    '99900011122',
    '$2a$10$MaisUmHashBcryptGeradoAqui5432167890',
    'CLIENTE',
    CURRENT_TIMESTAMP,
    FALSE
);

-- Inserindo 3 usuários de teste
CREATE SCHEMA IF NOT EXISTS app;
