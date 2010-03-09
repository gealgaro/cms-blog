SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `daily_money` ;
CREATE SCHEMA IF NOT EXISTS `daily_money` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `daily_money`;

-- -----------------------------------------------------
-- Table `daily_money`.`devices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`devices` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`devices` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type` INT NOT NULL DEFAULT 0 ,
  `name` VARCHAR(255) NOT NULL ,
  `unique_id` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_money`.`routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`routes` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`routes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_money`.`clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`clients` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`clients` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `identification` VARCHAR(255) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `phone` VARCHAR(255) NULL ,
  `cell` VARCHAR(255) NULL ,
  `address` VARCHAR(255) NULL ,
  `town` VARCHAR(255) NULL ,
  `city` VARCHAR(255) NULL ,
  `birthday` DATE NULL ,
  `comment` TEXT NULL ,
  `route_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `client_identification_index` (`identification` ASC) ,
  INDEX `client_route_id_index` (`route_id` ASC) ,
  INDEX `client_route_fk` (`route_id` ASC) ,
  CONSTRAINT `client_route_fk`
    FOREIGN KEY (`route_id` )
    REFERENCES `daily_money`.`routes` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_money`.`collectors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`collectors` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`collectors` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `identification` VARCHAR(255) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `phone` VARCHAR(255) NULL ,
  `cell` VARCHAR(255) NULL ,
  `address` VARCHAR(255) NULL ,
  `comment` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_money`.`credits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`credits` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`credits` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `created_at` DATETIME NOT NULL ,
  `first_payment_at` DATE NOT NULL ,
  `amount` DOUBLE NOT NULL ,
  `paycount` INT NOT NULL ,
  `payment` DOUBLE NOT NULL ,
  `client_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `credit_client_id_index` (`client_id` ASC) ,
  INDEX `credit_client_fk` (`client_id` ASC) ,
  CONSTRAINT `credit_client_fk`
    FOREIGN KEY (`client_id` )
    REFERENCES `daily_money`.`clients` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_money`.`collects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`collects` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`collects` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `created_at` DATE NOT NULL ,
  `initial` DOUBLE NOT NULL ,
  `new_loans` DOUBLE NULL ,
  `collected` DOUBLE NULL ,
  `expenses` DOUBLE NULL ,
  `new_loans_count` INT NULL ,
  `out_loans_count` INT NULL ,
  `final_loans_count` INT NULL ,
  `final_cash` DOUBLE NULL ,
  `route_id` INT NULL ,
  `collector_id` INT NULL ,
  `device_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `collect_route_id_index` (`route_id` ASC) ,
  INDEX `collect_collector_id_index` (`collector_id` ASC) ,
  INDEX `collect_device_id_index` (`device_id` ASC) ,
  INDEX `collect_route_fk` (`route_id` ASC) ,
  INDEX `collect_collector_fk` (`collector_id` ASC) ,
  INDEX `collect_device_fk` (`device_id` ASC) ,
  CONSTRAINT `collect_route_fk`
    FOREIGN KEY (`route_id` )
    REFERENCES `daily_money`.`routes` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `collect_collector_fk`
    FOREIGN KEY (`collector_id` )
    REFERENCES `daily_money`.`collectors` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `collect_device_fk`
    FOREIGN KEY (`device_id` )
    REFERENCES `daily_money`.`devices` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `daily_money`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `daily_money`.`payments` ;

CREATE  TABLE IF NOT EXISTS `daily_money`.`payments` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `amount` DOUBLE NOT NULL ,
  `credit_id` INT NOT NULL ,
  `collect_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `payment_credit_id_index` (`credit_id` ASC) ,
  INDEX `payment_collect_id_index` (`collect_id` ASC) ,
  INDEX `payment_credit_fk` (`credit_id` ASC) ,
  INDEX `payment_collect_fk` (`collect_id` ASC) ,
  CONSTRAINT `payment_credit_fk`
    FOREIGN KEY (`credit_id` )
    REFERENCES `daily_money`.`credits` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `payment_collect_fk`
    FOREIGN KEY (`collect_id` )
    REFERENCES `daily_money`.`collects` (`id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
