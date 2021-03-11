-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fieldproject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fieldproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fieldproject` DEFAULT CHARACTER SET utf8 ;
USE `fieldproject` ;

-- -----------------------------------------------------
-- Table `fieldproject`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`user` (
                                                     `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `user_email` VARCHAR(255) NOT NULL,
    `user_password` VARCHAR(255) NOT NULL,
    `user_nickname` VARCHAR(255) NOT NULL,
    `user_job` VARCHAR(255) NULL,
    `user_gender` VARCHAR(255) NULL,
    `user_birth` DATE NULL,
    `user_role` VARCHAR(255) NOT NULL DEFAULT 'ROLE_USER',
    `user_profile_img` TEXT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
    UNIQUE INDEX `user_nickname_UNIQUE` (`user_nickname` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`book` (
                                                     `book_isbn` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `book_title` VARCHAR(255) NOT NULL,
    `book_author` VARCHAR(255) NOT NULL,
    `book_publisher` VARCHAR(255) NOT NULL,
    `book_contents` TEXT NULL,
    `book_date` DATE NULL,
    `book_cover` TEXT NULL,
    PRIMARY KEY (`book_isbn`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`review` (
                                                       `review_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `book_isbn` BIGINT NULL,
                                                       `review_content` TEXT NOT NULL,
                                                       `review_rating` INT NULL,
                                                       PRIMARY KEY (`review_id`),
    INDEX `review_book_fk_idx` (`book_isbn` ASC) VISIBLE,
    CONSTRAINT `review_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`notice` (
                                                       `notice_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `notice_title` VARCHAR(255) NOT NULL,
    `notice_content` TEXT NOT NULL,
    `notice_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id` BIGINT NULL,
    PRIMARY KEY (`notice_id`),
    INDEX `notice_user_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `notice_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`wishlist` (
                                                         `wishlist_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                         `wishlist_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                         `user_id` BIGINT NULL,
                                                         `book_isbn` BIGINT NULL,
                                                         PRIMARY KEY (`wishlist_id`),
    INDEX `wishlist_user_fk_idx` (`user_id` ASC) VISIBLE,
    INDEX `wishlist_book_fk_idx` (`book_isbn` ASC) VISIBLE,
    CONSTRAINT `wishlist_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `wishlist_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`genre` (
                                                      `genre_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `genre_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`genre_id`),
    UNIQUE INDEX `genre_name_UNIQUE` (`genre_name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`user_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`user_genre` (
                                                           `user_genre_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                           `user_id` BIGINT NULL,
                                                           `genre_id` BIGINT NULL,
                                                           PRIMARY KEY (`user_genre_id`),
    INDEX `user_genre_user_fk_idx` (`user_id` ASC) VISIBLE,
    INDEX `user_genre_genre_fk_idx` (`genre_id` ASC) VISIBLE,
    CONSTRAINT `user_genre_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `user_genre_genre_fk`
    FOREIGN KEY (`genre_id`)
    REFERENCES `fieldproject`.`genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`book_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`book_genre` (
                                                           `book_genre_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                           `genre_id` BIGINT NULL,
                                                           `book_isbn` BIGINT NULL,
                                                           PRIMARY KEY (`book_genre_id`),
    INDEX `book_genre_book_fk_idx` (`book_isbn` ASC) VISIBLE,
    INDEX `book_genre_genre_fk_idx` (`genre_id` ASC) VISIBLE,
    CONSTRAINT `book_genre_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `book_genre_genre_fk`
    FOREIGN KEY (`genre_id`)
    REFERENCES `fieldproject`.`genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`user_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`user_book` (
                                                          `user_book_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                          `user_id` BIGINT NULL,
                                                          `book_isbn` BIGINT NULL,
                                                          PRIMARY KEY (`user_book_id`),
    INDEX `user_book_user_fk_idx` (`user_id` ASC) VISIBLE,
    INDEX `user_book_book_fk_idx` (`book_isbn` ASC) VISIBLE,
    CONSTRAINT `user_book_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `user_book_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fieldproject`.`highlight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`highlight` (
                                                          `highlight_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                          `highlight_content` TEXT NOT NULL,
                                                          `highlight_page` INT NULL,
                                                          `highlight_cover` TEXT NULL,
                                                          `book_isbn` BIGINT NULL,
                                                          `user_id` BIGINT NULL,
                                                          PRIMARY KEY (`highlight_id`),
    INDEX `highlight_user_fk_idx` (`user_id` ASC) VISIBLE,
    INDEX `highlight_book_fk_idx` (`book_isbn` ASC) VISIBLE,
    CONSTRAINT `highlight_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `highlight_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;