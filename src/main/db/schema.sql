-- MySQL Script generated by MySQL Workbench
-- Fri Feb  3 19:44:55 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema owp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `owp` ;

-- -----------------------------------------------------
-- Schema owp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `owp` DEFAULT CHARACTER SET utf8 ;
USE `owp` ;

-- -----------------------------------------------------
-- Table `owp`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owp`.`users` ;

CREATE TABLE IF NOT EXISTS `owp`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(300) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `name` VARCHAR(300) NOT NULL,
  `last_name` VARCHAR(300) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `jmbg` VARCHAR(13) NOT NULL,
  `address` VARCHAR(300) NOT NULL,
  `phone` VARCHAR(300) NOT NULL,
  `date_of_registration` VARCHAR(30) NOT NULL,
  `role` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  UNIQUE INDEX `jmbg` (`jmbg` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `owp`.`proizvodjaci`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owp`.`proizvodjaci` ;

CREATE TABLE IF NOT EXISTS `owp`.`proizvodjaci` (
  `proizvodjac_id` INT NOT NULL AUTO_INCREMENT,
  `proizvodjac` VARCHAR(30) NOT NULL,
  `drzavaProizvodnje` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`proizvodjac_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `owp`.`prijavezavakcinaciju`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owp`.`prijavezavakcinaciju` ;

CREATE TABLE IF NOT EXISTS `owp`.`prijavezavakcinaciju` (
  `ime` VARCHAR(30) NOT NULL,
  `prezime` VARCHAR(30) NOT NULL,
  `jmbg` VARCHAR(30) NOT NULL,
  `vakcina` VARCHAR(30) NOT NULL,
  `users_id` INT NOT NULL,
  `proizvodjaci_proizvodjac_id` INT NOT NULL,
  PRIMARY KEY (`jmbg`),
  INDEX `fk_prijavezavakcinaciju_users_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_prijavezavakcinaciju_proizvodjaci1_idx` (`proizvodjaci_proizvodjac_id` ASC) VISIBLE,
  CONSTRAINT `fk_prijavezavakcinaciju_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `owp`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prijavezavakcinaciju_proizvodjaci1`
    FOREIGN KEY (`proizvodjaci_proizvodjac_id`)
    REFERENCES `owp`.`proizvodjaci` (`proizvodjac_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `owp`.`vakcine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owp`.`vakcine` ;

CREATE TABLE IF NOT EXISTS `owp`.`vakcine` (
  `vakcina_id` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(30) NOT NULL,
  `kolicina` VARCHAR(30) NOT NULL DEFAULT '0',
  `drzavaProizvodnje` VARCHAR(30) NOT NULL,
  `datumPrijemaVakcine` DATE NOT NULL,
  `brojDoze` VARCHAR(30) NULL DEFAULT NULL,
  `prijavezavakcinaciju_jmbg` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`vakcina_id`),
  UNIQUE INDEX `naziv` (`naziv` ASC) VISIBLE,
  INDEX `fk_vakcine_prijavezavakcinaciju1_idx` (`prijavezavakcinaciju_jmbg` ASC) VISIBLE,
  CONSTRAINT `fk_vakcine_prijavezavakcinaciju1`
    FOREIGN KEY (`prijavezavakcinaciju_jmbg`)
    REFERENCES `owp`.`prijavezavakcinaciju` (`jmbg`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `owp`.`vesti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owp`.`vesti` ;

CREATE TABLE IF NOT EXISTS `owp`.`vesti` (
  `naziv` VARCHAR(30) NOT NULL,
  `sadrzaj` VARCHAR(255) NOT NULL,
  `vremeObjavljivanja` VARCHAR(100) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`naziv`),
  INDEX `fk_vesti_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_vesti_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `owp`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `owp`.`vesti_o_obolelima`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owp`.`vesti_o_obolelima` ;

CREATE TABLE IF NOT EXISTS `owp`.`vesti_o_obolelima` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `oboleliUPoslednja24h` VARCHAR(255) NOT NULL,
  `testiraniUPoslednja24h` VARCHAR(255) NOT NULL,
  `ukupnoObolelihOdStarta` VARCHAR(255) NOT NULL,
  `brojHospitalizovanih` VARCHAR(255) NOT NULL,
  `pacijentiNaRespiratoru` VARCHAR(255) NOT NULL,
  `vremeObjavljivanja` VARCHAR(255) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vesti_o_obolelima_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_vesti_o_obolelima_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `owp`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
