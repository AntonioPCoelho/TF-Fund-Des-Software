INSERT INTO Aplicativo (Id, nome, custo_mensal) VALUES
(1, 'AppFinanceiro', 29.99),
(2, 'AppEduca', 19.99),
(3, 'AppSaude', 39.99),
(4, 'AppMusica', 9.99),
(5, 'AppFitness', 24.99)


INSERT INTO Cliente (Id, nome, email) VALUES
(1, 'Carlos Silva', 'carlos.silva@example.com'),
(2, 'Ana Costa', 'ana.costa@example.com'),
(3, 'Lucas Martins', 'lucas.martins@example.com'),
(4, 'Laura Pereira', 'laura.pereira@example.com'),
(5, 'Paulo Souza', 'paulo.souza@example.com');

INSERT INTO Assinatura (Id, aplicativo_Id, cliente_Id, status, inicioVigencia, fimVigencia) VALUES
(1, 1, 1, 'Ativa', '2024-01-01', '2024-12-31'),
(2, 2, 2, 'Expirada', '2023-01-01', '2023-12-31'),
(3, 3, 3, 'Ativa', '2024-01-15', '2024-12-15'),
(4, 4, 4, 'Cancelada', '2024-02-01', '2024-12-31'),
(5, 5, 5, 'Ativa', '2024-03-01', '2024-12-31');

INSERT INTO Pagamento (Id, assinatura_Id, valorPago, dataPagamento, promocao) VALUES
(1, 1, 29.99, '2024-01-01', 'Desconto Anual'),
(2, 2, 19.99, '2023-01-01', 'Sem Desconto'),
(3, 3, 39.99, '2024-01-15', 'Promoção de Início'),
(4, 4, 9.99, '2024-02-01', 'Desconto de Cancelamento'),
(5, 5, 24.99, '2024-03-01', 'Desconto de Fidelidade');

INSERT INTO Usuario (usuario, senha) VALUES
('carlos_silva', 'senha123'),
('ana_costa', 'senha456'),
('lucas_martins', 'senha789'),
('laura_pereira', 'senha101'),
('paulo_souza', 'senha202');
