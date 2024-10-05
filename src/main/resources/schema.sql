CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE TipoCambio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    monedaOrigen VARCHAR(50) NOT NULL,
    monedaDestino VARCHAR(50) NOT NULL,
    tipoCambio DECIMAL(10, 4) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    montoConvertido DECIMAL(10, 2) NOT NULL,
    fecha TIMESTAMP NOT NULL,
    usuarioId BIGINT,
    FOREIGN KEY (usuarioId) REFERENCES Usuario(id) ON DELETE SET NULL
);
