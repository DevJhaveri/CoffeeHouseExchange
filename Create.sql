-- MySQL Script generated by MySQL Workbench
-- Sun Apr 15 04:57:12 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Entrepeneur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Entrepeneur` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Entrepeneur` (
  `idEntrepeneur` INT NOT NULL AUTO_INCREMENT,
  `EntRating` DOUBLE NULL,
  `EntDescrip` VARCHAR(45) NULL,
  `Ent_First_Name` VARCHAR(45) NOT NULL,
  `Ent_Last_Name` VARCHAR(45) NOT NULL,
  `Ent_SSN` VARCHAR(45) NOT NULL,
  `Ent_Sex` CHAR(1) NOT NULL,
  `Ent_DOB` DATE NOT NULL,
  `Ent_Join` DATE NOT NULL,
  `Ent_Rating` DOUBLE NULL,
  `Ent_Password` VARCHAR(15) NULL,
  `IsLoggedIn` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idEntrepeneur`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idEntrepeneur_UNIQUE` ON `mydb`.`Entrepeneur` (`idEntrepeneur` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`Venture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Venture` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Venture` (
  `idVenture` INT NOT NULL AUTO_INCREMENT,
  `Entrepeneur_idEntrepeneur` INT NOT NULL,
  `IsDebt` TINYINT(1) NOT NULL,
  `MaxUnits` INT NOT NULL,
  `RemainUnits` INT NOT NULL,
  `VentureDesc` VARCHAR(45) NULL,
  `PricePerUnit` DOUBLE NOT NULL,
  `StartDate` DATE NULL,
  `EndDate` DATE NULL,
  `VentureRating` DOUBLE NULL,
  PRIMARY KEY (`idVenture`, `Entrepeneur_idEntrepeneur`),
  CONSTRAINT `fk_Venture_Entrepeneur`
    FOREIGN KEY (`Entrepeneur_idEntrepeneur`)
    REFERENCES `mydb`.`Entrepeneur` (`idEntrepeneur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Venture_Entrepeneur_idx` ON `mydb`.`Venture` (`Entrepeneur_idEntrepeneur` ASC);

CREATE UNIQUE INDEX `idVenture_UNIQUE` ON `mydb`.`Venture` (`idVenture` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`Individual`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Individual` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Individual` (
  `idIndividual` INT NOT NULL AUTO_INCREMENT,
  `Ind_First_Name` VARCHAR(45) NOT NULL,
  `Ind_Last_Name` VARCHAR(45) NOT NULL,
  `Ind_Email` VARCHAR(45) NOT NULL,
  `Ind_SSN` VARCHAR(45) NOT NULL,
  `Ind_Rating` DOUBLE NULL,
  `Ind_DOB` DATE NOT NULL,
  `Ind_Join` DATE NOT NULL,
  `Ind_Balance` DOUBLE NULL,
  PRIMARY KEY (`idIndividual`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idIndividual_UNIQUE` ON `mydb`.`Individual` (`idIndividual` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`Corporation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Corporation` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Corporation` (
  `idCorporation` INT NOT NULL AUTO_INCREMENT,
  `CorporationDescrip` VARCHAR(45) NULL,
  `CorporationName` VARCHAR(45) NULL,
  PRIMARY KEY (`idCorporation`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idCorporation_UNIQUE` ON `mydb`.`Corporation` (`idCorporation` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`IndRelationship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`IndRelationship` ;

CREATE TABLE IF NOT EXISTS `mydb`.`IndRelationship` (
  `Individual_idIndividual` INT NOT NULL,
  `Venture_idVenture` INT NOT NULL,
  `IndReview` DOUBLE NULL,
  `UnitsOwned` INT NULL,
  `MonetaryValue` DOUBLE NULL,
  PRIMARY KEY (`Venture_idVenture`, `Individual_idIndividual`),
  CONSTRAINT `fk_Investment_Individual1`
    FOREIGN KEY (`Individual_idIndividual`)
    REFERENCES `mydb`.`Individual` (`idIndividual`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Investment_Venture1`
    FOREIGN KEY (`Venture_idVenture`)
    REFERENCES `mydb`.`Venture` (`idVenture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Investment_Individual1_idx` ON `mydb`.`IndRelationship` (`Individual_idIndividual` ASC);

CREATE INDEX `fk_Investment_Venture1_idx` ON `mydb`.`IndRelationship` (`Venture_idVenture` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`CorpRelationship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`CorpRelationship` ;

CREATE TABLE IF NOT EXISTS `mydb`.`CorpRelationship` (
  `Venture_idVenture` INT NOT NULL,
  `Corporation_idCorporation` INT NOT NULL,
  PRIMARY KEY (`Venture_idVenture`, `Corporation_idCorporation`),
  CONSTRAINT `fk_CorpInvestment_Venture1`
    FOREIGN KEY (`Venture_idVenture`)
    REFERENCES `mydb`.`Venture` (`idVenture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CorpInvestment_Corporation1`
    FOREIGN KEY (`Corporation_idCorporation`)
    REFERENCES `mydb`.`Corporation` (`idCorporation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CorpInvestment_Venture1_idx` ON `mydb`.`CorpRelationship` (`Venture_idVenture` ASC);

CREATE INDEX `fk_CorpInvestment_Corporation1_idx` ON `mydb`.`CorpRelationship` (`Corporation_idCorporation` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`EqVenture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`EqVenture` ;

CREATE TABLE IF NOT EXISTS `mydb`.`EqVenture` (
  `Venture_idVenture` INT NOT NULL,
  `PPS Initial` DOUBLE NOT NULL,
  `ExpectedReturn` DOUBLE NOT NULL,
  `PPS Now` DOUBLE NULL,
  PRIMARY KEY (`Venture_idVenture`),
  CONSTRAINT `fk_EqVenture_Venture1`
    FOREIGN KEY (`Venture_idVenture`)
    REFERENCES `mydb`.`Venture` (`idVenture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DebtVenture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DebtVenture` ;

CREATE TABLE IF NOT EXISTS `mydb`.`DebtVenture` (
  `Venture_idVenture` INT NOT NULL,
  `InterestRate` DOUBLE NOT NULL,
  `ExpiryDate` DATE NOT NULL,
  `Guarantee` DOUBLE NOT NULL,
  PRIMARY KEY (`Venture_idVenture`),
  CONSTRAINT `fk_DebtVenture_Venture1`
    FOREIGN KEY (`Venture_idVenture`)
    REFERENCES `mydb`.`Venture` (`idVenture`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`timestamps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`timestamps` ;

CREATE TABLE IF NOT EXISTS `mydb`.`timestamps` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
