-- Tabla: Usuarios
CREATE TABLE Usuarios (
id INT PRIMARY KEY,
nit VARCHAR(15) UNIQUE,
nombre VARCHAR(50),
email VARCHAR(100) UNIQUE,
fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla: Cuentas
CREATE TABLE Cuentas (
id INT PRIMARY KEY,
usuario_id INT,
saldo DECIMAL(15, 2),
tipo_cuenta ENUM('ahorro', 'corriente'),
FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

-- Tabla: Transacciones
CREATE TABLE Transacciones (
id INT PRIMARY KEY,
cuenta_origen_id INT,
cuenta_destino_id INT,
monto DECIMAL(15, 2),
fecha_transaccion DATETIME,
FOREIGN KEY (cuenta_origen_id) REFERENCES Cuentas(id),
FOREIGN KEY (cuenta_destino_id) REFERENCES Cuentas(id)
);

-- Ejemplo de registros de prueba para Usuarios
INSERT INTO Usuarios (id, nit, nombre, email)
VALUES
(1, '123456789', 'Juan Pérez', 'juan@example.com'),
(2, '987654321', 'María López', 'maria@example.com');

-- Ejemplo de registros de prueba para Cuentas
INSERT INTO Cuentas (id, usuario_id, saldo, tipo_cuenta)
VALUES
(1, 1, 1000.00, 'corriente'),
(2, 1, 5000.00, 'ahorro'),
(3, 2, 2500.00, 'corriente');

-- Ejemplo de registros de prueba para Transacciones
INSERT INTO Transacciones (id, cuenta_origen_id, cuenta_destino_id, monto, fecha_transaccion)
VALUES
(1, 1, 2, 500.00, '2023-03-10 09:30:00'),
(2, 2, 1, 100.00, '2023-03-12 15:45:00'),
(3, 3, 1, 700.00, '2023-03-15 11:20:00');