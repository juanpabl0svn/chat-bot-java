
CREATE TABLE IF NOT EXISTS users (
user_id INT PRIMARY KEY AUTO_INCREMENT,
nit VARCHAR(15) UNIQUE NOT NULL,
name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS accounts (
account_id INT PRIMARY KEY,
owner_nit VARCHAR(15)NOT NULL,
balance DECIMAL(15, 2) DEFAULT 0,
password VARCHAR(20) NOT NULL,
FOREIGN KEY (owner_nit) REFERENCES users(nit)
);

CREATE TABLE IF NOT EXISTS transactions (
transaaction_id INT PRIMARY KEY,
root_account INT NOT NULL,
destination_account INT NOT NULL,
amount DECIMAL(15, 2) NOT NULL,
transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (root_account) REFERENCES accounts(account_id),
FOREIGN KEY (destination_account) REFERENCES accounts(account_id)
);

-- Insertar registros en la tabla 'users'
INSERT INTO users (nit, name, surname, email) VALUES
('1234', 'Juan', 'Pérez', 'juan@example.com'),
('5678', 'María', 'López', 'maria@example.com'),
('9123', 'Carlos', 'Gómez', 'carlos@example.com'),
('4567', 'Laura', 'Martínez', 'laura@example.com'),
('8912', 'Andrés', 'Rodríguez', 'andres@example.com');

-- Insertar registros en la tabla 'accounts'
INSERT INTO accounts (account_id, owner_nit, balance, password) VALUES
(1, '1234', 1500.00, 'pass123'),
(2, '5678', 2500.00, 'secure456'),
(3, '9123', 750.00, 'mypass789'),
(4, '4567', 3000.00, 'secret567'),
(5, '8912', 500.00, 'access987');

-- Insertar registros en la tabla 'transactions'
INSERT INTO transactions (transaaction_id, root_account, destination_account, amount) VALUES
(1, 1, 2, 200.00),
(2, 3, 4, 50.00),
(3, 2, 5, 100.00),
(4, 4, 1, 500.00),
(5, 5, 3, 25.00);

