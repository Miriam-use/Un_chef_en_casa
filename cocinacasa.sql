-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 01-06-2021 a las 08:54:00
-- Versión del servidor: 5.6.13
-- Versión de PHP: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `cocinacasa`
--
CREATE DATABASE IF NOT EXISTS `cocinacasa` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cocinacasa`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favorito`
--

CREATE TABLE IF NOT EXISTS `favorito` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idreceta` varchar(255) NOT NULL,
  `idusuario` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `favorito`
--

INSERT INTO `favorito` (`id`, `idreceta`, `idusuario`) VALUES
(1, '1', '3'),
(2, '4', '3'),
(8, '1', '1'),
(4, '2', '1'),
(5, '5', '1'),
(6, '1', '2'),
(7, '6', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedorviajes`
--

CREATE TABLE IF NOT EXISTS `proveedorviajes` (
  `idtableproveedorviajes` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtableproveedorviajes`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

CREATE TABLE IF NOT EXISTS `receta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `detalle` varchar(255) NOT NULL,
  `favorito` int(11) NOT NULL,
  `idusuario` varchar(255) NOT NULL,
  `lista` varchar(10000) NOT NULL,
  `mesas` varchar(255) NOT NULL,
  `pasos` varchar(10000) NOT NULL,
  `tiempo` varchar(255) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cueies2eo6c4os81o00hhabiw` (`mesas`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`id`, `detalle`, `favorito`, `idusuario`, `lista`, `mesas`, `pasos`, `tiempo`, `titulo`) VALUES
(1, 'tortilla de papas', 0, '1', 'papas, aceite, huevos', '3pers', 'prueba prueba prueba prueba,prueba', '25min', 'Tortilla'),
(2, 'pasta', 0, '1', 'pasta', '2', 'pasta, agua, ingredientes', '25min', 'Pasta'),
(3, 'Patatas rellenas de atún, beicon y queso', 0, '3', '4 patatas medianas 175 g c/u\n125 g de atún\n100 g de queso en lonchas tipo sándwich\n150 g de queso rallado\n4 lonchas de bacon o panceta\n4 pimientos del piquillo asados en conserva\n3 cucharadas de salsa de tomate\nsal\norégano', '4pers', 'Hierve las patatas. Coloca las patatas bien lavadas en una olla con agua al fuego y déjalas que hiervan hasta que estén bien tiernas por dentro que lo sabrás cuando, al pinchar con un tenedor, la patata se suelte con facilidad. Yo las he tenido 40 minutos', '55min', 'Patatas rellenas'),
(4, 'Pizza casera', 0, '2', 'agua, sal, harina, huevo', '2pers', 'poner la harina en un cacharro y añadirle el huevo y la sal. Añadir el agua gradualmente', '1hora 32min', 'Pizza'),
(5, 'arroz', 0, '1', 'arroz, agua', '4 pers', 'Poner una olla con agua a herví. Después añadí el arroz', '25min', 'Arroz Blanco'),
(6, 'Lasaña de carne casera', 0, '3', '500 g. de carne, 2 pimientos rojos, 2 zanahorias, 2 dientes de ajo, 150 g. de bacon o panceta, 2 cebollas grandes, 250 g. de tomate natural, 100 ml. de aceite de oliva virgen extra', '6pers', 'Calentamos una cazuela grande de agua, la más ancha de casa. Cuando empiece a hervir echamos 2 puñados generosos de sal. Introducimos las láminas de lasaña una a una sin que se toquen (para que no se peguen entre ellas).', '55min', 'Lasaña'),
(7, 'Chuletas de cerdo con salsa de cebolla', 0, '3', '500g de carne de cerdo (chuletas de cabeza de lomo sin hueso), 1 cebolla, 100 ml de coñac o vino blando, aceite de oliva, sal, pimienta negra molida, perejil picado para la decoración', '4pers.', 'En una sartén con aceite de oliva, fríe las chuletas de cerdo hasta que tomen un color dorado. Ten cuidado de no secarla demasiado. Una vez que hayan tomado el color tostado, añade la cebolla cortada en juliana. Deja que se sofría durante dos minutos. Mue', '45 min', 'Chuletas de cerdo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `idtableroles` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idtableroles`),
  UNIQUE KEY `UK_ldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idtableusuarios` bigint(20) NOT NULL AUTO_INCREMENT,
  `bcaddress` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `create_at` date NOT NULL,
  `dasatos` varchar(8) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `usuariodetalle` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idtableusuarios`),
  KEY `FK1aru20drp71skkpq1ewcnqbf3` (`usuariodetalle`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idtableusuarios`, `bcaddress`, `contrasena`, `correo`, `create_at`, `dasatos`, `enabled`, `usuariodetalle`) VALUES
(1, NULL, '1', 'prueba@gmail.com', '2021-05-31', 'AAAAAAAA', NULL, 1),
(2, NULL, '1q2w3e4r', 'miriam@gmail.com', '2021-05-31', 'MIRIAM', NULL, 2),
(3, NULL, '1q2w3e4r', 'ana@ana.com', '2021-05-31', 'AANAA', NULL, 3),
(8, NULL, '1', 'prueba@gmail.com', '2021-06-01', 'AZ786193', NULL, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariosdetalle`
--

CREATE TABLE IF NOT EXISTS `usuariosdetalle` (
  `idtableusuariosdetalle` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT 'Prueba',
  `nombre` varchar(255) DEFAULT NULL,
  `oficina` varchar(255) DEFAULT 'Prueba',
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtableusuariosdetalle`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `usuariosdetalle`
--

INSERT INTO `usuariosdetalle` (`idtableusuariosdetalle`, `apellidos`, `direccion`, `nombre`, `oficina`, `rol`) VALUES
(1, 'Prueba2', 'Prueba', 'Prueba1', 'Prueba', 'Administrador'),
(2, 'Mateos', 'Prueba', 'Miriam', 'Prueba', 'Usuario'),
(3, 'Perez', 'Prueba', 'Ana', 'Prueba', 'Usuario'),
(8, 'm', 'Prueba', 'jose', 'Prueba', 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

CREATE TABLE IF NOT EXISTS `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKqjaspm7473pnu9y4jxhrds8r2` (`usuario_id`,`role_id`),
  KEY `FKihom0uklpkfpffipxpoyf7b74` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajesuber`
--

CREATE TABLE IF NOT EXISTS `viajesuber` (
  `idtableviajesuber` bigint(20) NOT NULL AUTO_INCREMENT,
  `consumo` double NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `distancia` double NOT NULL,
  `ecocoins` bigint(20) DEFAULT NULL,
  `fecha` date NOT NULL,
  `fechaida` date DEFAULT NULL,
  `huellaemision` varchar(255) DEFAULT NULL,
  `huellasalvada` varchar(255) DEFAULT NULL,
  `idreceipt` varchar(255) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `origen` varchar(255) DEFAULT NULL,
  `precio` float NOT NULL,
  `requestid` varchar(255) DEFAULT NULL,
  `rideid` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tipotransporte` varchar(255) DEFAULT NULL,
  `txdataecocoins` varchar(255) DEFAULT NULL,
  `txdataviaje` varchar(255) DEFAULT NULL,
  `proveedor` bigint(20) NOT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idtableviajesuber`),
  KEY `FKj84x5x2qu3q0cqhl0i8brufk0` (`proveedor`),
  KEY `FKg9vi0rcywpp0ksfl07mnr2wam` (`usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
