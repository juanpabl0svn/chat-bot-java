CREATE DATABASE users;

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
FOREIGN KEY (nit) REFERENCES users(nit)
);

-- Insertar registros en la tabla 'users'
INSERT INTO users (nit, name, surname, email) VALUES
('1234', 'JUAN', 'PEREZ', 'JUANP@EXAMPLE.COM'),
('5678', 'MARIA', 'López', 'MARIA@EXAMPLE.COM'),
('9123', 'CARLOS', 'GOMEZ', 'CARLOS@EXAMPLE.COM'),
('4567', 'LAURA', 'MARTINEZ', 'LAURA@EXAMPLE.COM'),
('8912', 'ANDRES', 'RODRIGUEZ', 'ANDREZ@EXAMPLE.COM');

-- Insertar registros en la tabla 'accounts'
INSERT INTO accounts (number, owner_nit, balance,debt, username, password) VALUES
('1234567890', '1234', 1500.00, 0,'USER1', '7yzdb5dSkTU='),
('2345678901', '5678', 2500.00,2000 ,'USER2', '0JdVrWQcJ+g='),  
('3456789012', '9123', 750.00, 300,'USER3', 'bTD+kxrIekM='),  
('4567890123', '4567', 3000.00, 15,'USER4', '2PbqmaTPgWM='),   
('5678901234', '8912', 500.00, 800,'USER5', 'r+zrEbjRtB0='); 