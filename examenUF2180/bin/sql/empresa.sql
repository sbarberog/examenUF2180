CREATE DATABASE  IF NOT EXISTS `empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `empresa`;

DROP TABLE IF EXISTS `centros`;
CREATE TABLE `centros` (
  `cod_centro` smallint unsigned NOT NULL,
  `nombre` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `direccion` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`cod_centro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `centros` VALUES (10,'SEDE CENTRAL','C. ALCALA 820, MADRID'),
	(20,'RELACION CON CLIENTES','C. ATOCHA 405, MADRID'),
	(30,'ALMACÉN  I','C, CHAMARTÍN 25, MADRID');

DROP TABLE IF EXISTS `departamentos`;
CREATE TABLE `departamentos` (
  `cod_departamento` smallint unsigned NOT NULL,
  `cod_centro` smallint unsigned DEFAULT NULL,
  `tipo_dir` char(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `presupuesto` smallint DEFAULT NULL,
  `cod_dpto_jefe` smallint unsigned DEFAULT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`cod_departamento`),
  KEY `fk_departamentos_cod_centro` (`cod_centro`),
  KEY `fk_departamentos_cod_dpto_jefe` (`cod_dpto_jefe`),
  CONSTRAINT `fk_departamentos_cod_centro` FOREIGN KEY (`cod_centro`) REFERENCES `centros` (`cod_centro`),
  CONSTRAINT `fk_departamentos_cod_dpto_jefe` FOREIGN KEY (`cod_dpto_jefe`) REFERENCES `departamentos` (`cod_departamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `departamentos` VALUES 
        (100,10,'P',12,NULL,'DIRECCION GENERAL'),
		(110,20,'P',15,100,'DIRECC. COMERCIAL'),
        (111,20,'F',11,110,'SECTOR INDUSTRIAL'),
        (112,20,'P',9,100,'SECTOR SERVICIOS'),
        (120,10,'F',3,100,'ORGANIZACION'),
        (121,10,'P',2,120,'PERSONAL'),
        (122,10,'P',6,120,'PROCESO DE DATOS'),
        (130,10,'P',2,100,'FINANZAS');

