INSERT INTO app.usuario (
    nome,
    email,
    login,
    cpf,
    senha_hash,
    tipo_usuario,
    status,
    data_ultima_alteracao,
    ativo
)
SELECT
    'Administrador Master',
    'admin@sistema.com',
    'admin.master',
    '00000000001',
    '$2a$10$nHxyaINXlotBVrfJtWA72ezQW2Zt7EGQToopoQca/hy4A4imKvUtC',
    'ADMINISTRADOR',
    'ATIVO',
    CURRENT_TIMESTAMP,
    true
    WHERE NOT EXISTS (
    SELECT 1
    FROM app.usuario
    WHERE login = 'admin.master'
       OR email = 'admin@sistema.com'
);