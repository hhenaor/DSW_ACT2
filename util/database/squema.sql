-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-04-2025 -- Fecha simulada
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mpnotes_db`
--
-- DROP DATABASE IF EXISTS `mpnotes_db`;
CREATE DATABASE IF NOT EXISTS `mpnotes_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `mpnotes_db`;
DROP TABLE IF EXISTS `courses`, `students`, `note_rules`, `note_registers`, `user_statuses`, `user_registrations`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
-- * Registro de los estudiantes, que pueden ser estudiantes
CREATE TABLE `students` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `courses`
-- * Registro de los cursos disponibles, creados por usuarios
CREATE TABLE `courses` (
  `course_id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `knowledge_area` varchar(255) NOT NULL,
  `career` varchar(255) NOT NULL,
  `credits` int(255) NOT NULL,
  `thematic_content` varchar(255) NOT NULL,
  `semester` varchar(255) NOT NULL,
  `professor` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `idx_fk_user_id` (`user_id`),
  CONSTRAINT `fk_courses_students`
    FOREIGN KEY (`user_id`) REFERENCES `students` (`username`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;