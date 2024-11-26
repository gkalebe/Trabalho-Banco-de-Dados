CREATE TABLE usuario (
                         id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         cpf VARCHAR(11) NOT NULL UNIQUE,
                         data_nascimento DATE NOT NULL,
                         telefone VARCHAR(15),
                         tipo_usuario ENUM('FUNCIONARIO', 'CLIENTE') NOT NULL,
                         senha VARCHAR(50) NOT NULL
);

CREATE TABLE funcionario (
                             id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
                             codigo_funcionario VARCHAR(20) NOT NULL,
                             cargo VARCHAR(50) NOT NULL,
                             id_usuario INT NOT NULL,
                             FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE cliente (
                         id_cliente INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         cpf VARCHAR(11) NOT NULL UNIQUE,
                         data_nascimento DATE NOT NULL,
                         telefone VARCHAR(15),
                         tipo_usuario ENUM('FUNCIONARIO', 'CLIENTE') NOT NULL,
                         senha VARCHAR(50) NOT NULL
);

CREATE TABLE endereco (
                          id_endereco INT AUTO_INCREMENT PRIMARY KEY,
                          cep VARCHAR(10) NOT NULL,
                          local VARCHAR(100) NOT NULL,
                          numero_casa INT NOT NULL,
                          bairro VARCHAR(50) NOT NULL,
                          cidade VARCHAR(50) NOT NULL,
                          estado VARCHAR(2) NOT NULL,
                          id_usuario INT NOT NULL,
                          FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE conta (
                       id_conta INT AUTO_INCREMENT PRIMARY KEY,
                       numero_conta VARCHAR(20) NOT NULL UNIQUE,
                       agencia VARCHAR(10) NOT NULL,
                       saldo DECIMAL(15, 2) DEFAULT 0,
                       tipo_conta ENUM('POUPANCA', 'CORRENTE') NOT NULL,
                       id_cliente INT NOT NULL,
                       FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE conta_corrente (
                                id_conta_corrente INT AUTO_INCREMENT PRIMARY KEY,
                                limite DECIMAL(15, 2) NOT NULL,
                                data_vencimento DATE NOT NULL,
                                id_conta INT NOT NULL,
                                FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
);

CREATE TABLE conta_poupanca (
                                id_conta_poupanca INT AUTO_INCREMENT PRIMARY KEY,
                                taxa_rendimento DECIMAL(5, 2) NOT NULL,
                                id_conta INT NOT NULL,
                                FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
);

CREATE TABLE transacao (
                           id_transacao INT AUTO_INCREMENT PRIMARY KEY,
                           tipo_transacao ENUM('DEPOSITO', 'SAQUE', 'TRANSFERENCIA') NOT NULL,
                           valor DECIMAL(15, 2) NOT NULL,
                           data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           id_conta INT NOT NULL,
                           FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
);

CREATE TABLE relatorio (
                           id_relatorio INT AUTO_INCREMENT PRIMARY KEY,
                           tipo_relatorio VARCHAR(50) NOT NULL,
                           data_geracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           conteudo TEXT NOT NULL,
                           id_funcionario INT NOT NULL,
                           FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);
