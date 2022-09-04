-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-09-2022 a las 02:30:14
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `natural`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huellas`
--

CREATE TABLE `huellas` (
  `h_id` int(5) NOT NULL,
  `h_nom` varchar(20) NOT NULL,
  `h_ocu` varchar(30) NOT NULL,
  `h_fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `huellas`
--

INSERT INTO `huellas` (`h_id`, `h_nom`, `h_ocu`, `h_fecha`) VALUES
(1, 'BRYAN', 'GERENTE', '2021-09-02'),
(2, 'EDWIN', 'GERENTE', '2021-09-02'),
(3, 'PAUL', 'GERENTE', '2021-09-02'),
(5, 'SANTIAGO', 'EMPLEADO', '2021-09-02'),
(6, 'ALISON', 'GERENCIA', '2021-09-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso`
--

CREATE TABLE `ingreso` (
  `i_usuario` varchar(20) NOT NULL,
  `i_fecha` date NOT NULL,
  `i_hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ingreso`
--

INSERT INTO `ingreso` (`i_usuario`, `i_fecha`, `i_hora`) VALUES
('USUARIO 1', '2021-09-02', '00:38:08'),
('USUARIO 1', '2021-09-02', '00:38:22'),
('USUARIO 2', '2021-09-02', '00:38:26'),
('USUARIO 3', '2021-09-02', '00:38:37'),
('USUARIO 5', '2021-09-02', '00:38:42'),
('USUARIO 1', '2021-09-02', '07:32:36'),
('USUARIO 1', '2021-09-02', '07:32:47'),
('USUARIO 2', '2021-09-02', '07:32:58'),
('USUARIO 2', '2021-09-02', '07:33:03'),
('USUARIO 1', '2021-09-02', '07:33:12'),
('USUARIO 1', '2021-09-03', '23:11:28'),
('USUARIO 1', '2021-09-03', '23:11:48'),
('USUARIO 1', '2021-09-03', '23:12:54'),
('USUARIO 1', '2021-09-03', '23:13:21'),
('USUARIO 6', '2021-09-03', '23:14:40'),
('USUARIO 6', '2021-09-03', '23:14:52'),
('USUARIO 6', '2021-09-03', '23:15:05'),
('USUARIO 6', '2021-09-03', '23:15:26'),
('USUARIO 6', '2021-09-03', '23:15:31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `tipo_id` int(1) NOT NULL,
  `tipo_nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`tipo_id`, `tipo_nombre`) VALUES
(1, 'Administrador'),
(2, 'Empleado'),
(3, 'Contador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usu_num` int(5) NOT NULL,
  `usu_nom` varchar(50) DEFAULT NULL,
  `usu_contra` varchar(42) DEFAULT NULL,
  `usu_tipo` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usu_num`, `usu_nom`, `usu_contra`, `usu_tipo`) VALUES
(9, 'admin1', 'admin1', 1),
(10, 'bryan', 'puma', 2),
(11, 'Puma', '1234', 2),
(12, 'EDWIN', '123456789', 1),
(13, 'Bryan Puma', '1004606545', 2),
(14, 'bryanpuma', '123456', 1),
(15, 'BRYANPUMA1', '011018', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `huellas`
--
ALTER TABLE `huellas`
  ADD PRIMARY KEY (`h_id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`tipo_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usu_num`),
  ADD KEY `usu_tipo` (`usu_tipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usu_num` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`usu_tipo`) REFERENCES `tipousuario` (`tipo_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
