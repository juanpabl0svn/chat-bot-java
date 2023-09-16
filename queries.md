CREATE TABLE IF NOT EXISTS users (
user_id INT PRIMARY KEY AUTO_INCREMENT,
nit VARCHAR(15) UNIQUE NOT NULL,
name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS accounts (
account_id INT PRIMARY KEY AUTO_INCREMENT,
number VARCHAR(10) NOT NULL,
owner_nit VARCHAR(15) NOT NULL,
balance DECIMAL(15, 2) DEFAULT 0,
debt DECIMAL(15, 2) DEFAULT 0,
username VARCHAR(20) NOT NULL,
password VARCHAR(50) NOT NULL,
active BOOLEAN DEFAULT 1,
FOREIGN KEY (owner_nit) REFERENCES users(nit)
);

CREATE TABLE IF NOT EXISTS pqrs(
id_pqrs INT PRIMARY KEY AUTO_INCREMENT,
nit VARCHAR(15) not null,
context VARCHAR(255) NOT NULL,
FOREIGN KEY (id_user) REFERENCES users(nit)
);

-- Insertar registros en la tabla 'users'
INSERT INTO users (nit, name, surname, email) VALUES
('1234', 'Juan', 'Pérez', 'juan@example.com'),
('5678', 'María', 'López', 'maria@example.com'),
('9123', 'Carlos', 'Gómez', 'carlos@example.com'),
('4567', 'Laura', 'Martínez', 'laura@example.com'),
('8912', 'Andrés', 'Rodríguez', 'andres@example.com');

-- Insertar registros en la tabla 'accounts'
INSERT INTO accounts (number, owner_nit, balance,debt, username, password) VALUES
('1234567890', '1234', 1500.00, 'user1', '7yzdb5dSkTU='),   --1234
('2345678901', '5678', 2500.00, 'user2', '0JdVrWQcJ+g='),   --2345
('3456789012', '9123', 750.00, 'user3', 'bTD+kxrIekM='),    --2525
('4567890123', '4567', 3000.00, 'user4', '2PbqmaTPgWM='),   --0000
('5678901234', '8912', 500.00, 'user5', 'r+zrEbjRtB0=');    --2020

