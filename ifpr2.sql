-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:33306
-- Tempo de geração: 13/09/2024 às 23:45
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `ifpr2`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`) VALUES
(1, 'Hardware'),
(2, 'Periféricos'),
(3, 'Softwares');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL,
  `data` datetime(6) DEFAULT NULL,
  `entrega` varchar(255) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pedido`
--

INSERT INTO `pedido` (`id`, `data`, `entrega`, `valor`, `usuario_id`) VALUES
(4, '2024-09-10 10:09:00.000000', 'Rua azul', 100, 1),
(5, '2024-09-10 10:09:00.000000', 'Sarna', 500, 2),
(6, '2024-09-10 10:09:00.000000', 'asdasdasdasdasdasdasd', 5000, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido_produto`
--

CREATE TABLE `pedido_produto` (
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `valor` float NOT NULL,
  `categoria_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto`
--

INSERT INTO `produto` (`id`, `nome`, `valor`, `categoria_id`) VALUES
(1, 'Placa Mãe', 450, 1),
(2, 'Processador Intel i7', 1300, 1),
(3, 'Memória RAM 16GB', 600, 1),
(4, 'HD 1TB', 350, 1),
(5, 'SSD 500GB', 500, 1),
(6, 'Fonte 500W', 250, 1),
(7, 'Placa de Vídeo GTX 1660', 1800, 1),
(8, 'Gabinete ATX', 200, 1),
(9, 'Mouse Gamer', 120, 2),
(10, 'Teclado Mecânico', 300, 2),
(11, 'Monitor 24 polegadas', 800, 2),
(12, 'Fone de Ouvido', 150, 2),
(13, 'Webcam HD', 180, 2),
(14, 'Impressora Multifuncional', 600, 2),
(15, 'Mesa Digitalizadora', 450, 2),
(16, 'Sistema Operacional Windows 10', 800, 3),
(17, 'Antivírus Kaspersky', 120, 3),
(18, 'Microsoft Office 365', 450, 3),
(19, 'Adobe Photoshop', 500, 3),
(20, 'AutoCAD', 3500, 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `chave` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `chave`, `email`, `nome`, `senha`) VALUES
(1, 'eed2d4b4de9481f5', 'ronaldo@jmail.com', 'Ronaldo', '$2a$16$X5WGkittfZ0s4Lnyr6ZFU.zEhM12.jpf7lMkfnQaQaugIL0gGM2xa'),
(2, 'ff0ca7ac7ccf53fb', 'isabela@hotmail.com', 'Isabela', '$2a$16$R7/QKUqJm0fttNPXkjLA/OfXQqrVTsu2uZ3dxQ8lQjGwdllfAQ.3K'),
(3, '4e5fa407955ee959', 'nicolas@hotmail.com', 'NicolasD', '$2a$16$khku4qApRPckF33jyK.Odu3hm52ma/39h4ZptQqwde4Q8CCrGBwke');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6uxomgomm93vg965o8brugt00` (`usuario_id`);

--
-- Índices de tabela `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD PRIMARY KEY (`pedido_id`,`produto_id`),
  ADD KEY `FKf8l3k06bmjhdwd79t0ndcw7tt` (`produto_id`);

--
-- Índices de tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKopu9jggwnamfv0c8k2ri3kx0a` (`categoria_id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK6uxomgomm93vg965o8brugt00` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Restrições para tabelas `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `FKcsbxw0y9i3wfmiupq9eqfpdtc` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKf8l3k06bmjhdwd79t0ndcw7tt` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);

--
-- Restrições para tabelas `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `FKopu9jggwnamfv0c8k2ri3kx0a` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
