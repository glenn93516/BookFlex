-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fieldproject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fieldproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fieldproject` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fieldproject` ;

-- -----------------------------------------------------
-- Table `fieldproject`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`book` (
  `book_isbn` BIGINT NOT NULL AUTO_INCREMENT,
  `book_title` VARCHAR(255) NOT NULL,
  `book_author` VARCHAR(255) NOT NULL,
  `book_publisher` VARCHAR(255) NOT NULL,
  `book_contents` TEXT NULL DEFAULT NULL,
  `book_date` DATE NULL DEFAULT NULL,
  `book_cover` TEXT NULL DEFAULT NULL,
  `book_description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`book_isbn`))
ENGINE = InnoDB
AUTO_INCREMENT = 9798995556191
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`genre` (
  `genre_id` BIGINT NOT NULL AUTO_INCREMENT,
  `genre_name` VARCHAR(255) NOT NULL,
  `genre_category` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE INDEX `genre_name_UNIQUE` (`genre_name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`book_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`book_genre` (
  `book_genre_id` BIGINT NOT NULL AUTO_INCREMENT,
  `genre_id` BIGINT NULL DEFAULT NULL,
  `book_isbn` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`book_genre_id`),
  UNIQUE INDEX `genre_id` (`genre_id` ASC, `book_isbn` ASC) VISIBLE,
  INDEX `book_genre_book_fk_idx` (`book_isbn` ASC) VISIBLE,
  INDEX `book_genre_genre_fk_idx` (`genre_id` ASC) VISIBLE,
  CONSTRAINT `book_genre_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`),
  CONSTRAINT `book_genre_genre_fk`
    FOREIGN KEY (`genre_id`)
    REFERENCES `fieldproject`.`genre` (`genre_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 189472
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`genre_new`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`genre_new` (
  `genre_id` BIGINT NOT NULL AUTO_INCREMENT,
  `genre_name` VARCHAR(255) NOT NULL,
  `genre_category` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE INDEX `genre_name_UNIQUE` (`genre_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(255) NOT NULL,
  `user_password` VARCHAR(255) NOT NULL,
  `user_nickname` VARCHAR(255) NOT NULL,
  `user_job` VARCHAR(255) NULL DEFAULT NULL,
  `user_gender` VARCHAR(255) NULL DEFAULT NULL,
  `user_birth` DATE NULL DEFAULT NULL,
  `user_role` VARCHAR(255) NOT NULL DEFAULT 'ROLE_USER',
  `user_profile_img` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `user_nickname_UNIQUE` (`user_nickname` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 155
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`highlight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`highlight` (
  `highlight_id` BIGINT NOT NULL AUTO_INCREMENT,
  `highlight_content` TEXT NOT NULL,
  `highlight_page` INT NULL DEFAULT NULL,
  `highlight_cover` TEXT NULL DEFAULT NULL,
  `book_isbn` BIGINT NULL DEFAULT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `is_public` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`highlight_id`),
  INDEX `highlight_user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `highlight_book_fk_idx` (`book_isbn` ASC) VISIBLE,
  CONSTRAINT `highlight_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`),
  CONSTRAINT `highlight_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`good`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`good` (
  `good_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL DEFAULT NULL,
  `highlight_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`good_id`),
  UNIQUE INDEX `user_id` (`user_id` ASC, `highlight_id` ASC) VISIBLE,
  INDEX `good_user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `good_highlight_fk_idx` (`highlight_id` ASC) VISIBLE,
  CONSTRAINT `good_highlight_fk`
    FOREIGN KEY (`highlight_id`)
    REFERENCES `fieldproject`.`highlight` (`highlight_id`),
  CONSTRAINT `good_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`notice` (
  `notice_id` BIGINT NOT NULL AUTO_INCREMENT,
  `notice_title` VARCHAR(255) NOT NULL,
  `notice_content` TEXT NOT NULL,
  `notice_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`),
  INDEX `notice_user_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `notice_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`receivemsg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`receivemsg` (
  `receivemsg_id` BIGINT NOT NULL AUTO_INCREMENT,
  `receivemsg_content` TEXT NOT NULL,
  `sender_id` BIGINT NULL DEFAULT NULL,
  `receiver_id` BIGINT NULL DEFAULT NULL,
  `receivemsg_date` DATETIME NULL DEFAULT NULL,
  `receivemsg_read` TINYINT NULL DEFAULT '0',
  PRIMARY KEY (`receivemsg_id`),
  INDEX `send_user_sender_id_fk_idx` (`sender_id` ASC) VISIBLE,
  INDEX `send_user_receiver_id_fk_idx` (`receiver_id` ASC) VISIBLE,
  CONSTRAINT `receive_user_receiver_id_fk`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `fieldproject`.`user` (`user_id`),
  CONSTRAINT `receive_user_sender_id_fk`
    FOREIGN KEY (`sender_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`review` (
  `review_id` BIGINT NOT NULL AUTO_INCREMENT,
  `book_isbn` BIGINT NULL DEFAULT NULL,
  `review_content` TEXT NOT NULL,
  `review_rating` INT NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `review_book_fk_idx` (`book_isbn` ASC) VISIBLE,
  CONSTRAINT `review_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`))
ENGINE = InnoDB
AUTO_INCREMENT = 1108167
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`sendmsg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`sendmsg` (
  `sendmsg_id` BIGINT NOT NULL AUTO_INCREMENT,
  `sendmsg_content` TEXT NOT NULL,
  `sender_id` BIGINT NULL DEFAULT NULL,
  `receiver_id` BIGINT NULL DEFAULT NULL,
  `sendmsg_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sendmsg_id`),
  INDEX `send_user_seder_id_fk_idx` (`sender_id` ASC) VISIBLE,
  INDEX `send_user_receiver_id_fk_idx` (`receiver_id` ASC) VISIBLE,
  CONSTRAINT `send_user_receiver_id_fk`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `fieldproject`.`user` (`user_id`),
  CONSTRAINT `send_user_seder_id_fk`
    FOREIGN KEY (`sender_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`user_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`user_book` (
  `user_book_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL DEFAULT NULL,
  `book_isbn` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`user_book_id`),
  UNIQUE INDEX `user_id` (`user_id` ASC, `book_isbn` ASC) VISIBLE,
  INDEX `user_book_user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `user_book_book_fk_idx` (`book_isbn` ASC) VISIBLE,
  CONSTRAINT `user_book_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`),
  CONSTRAINT `user_book_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 117
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`user_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`user_genre` (
  `user_genre_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL DEFAULT NULL,
  `genre_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`user_genre_id`),
  UNIQUE INDEX `user_id` (`user_id` ASC, `genre_id` ASC) VISIBLE,
  INDEX `user_genre_user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `user_genre_genre_fk_idx` (`genre_id` ASC) VISIBLE,
  CONSTRAINT `user_genre_genre_fk`
    FOREIGN KEY (`genre_id`)
    REFERENCES `fieldproject`.`genre` (`genre_id`),
  CONSTRAINT `user_genre_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 559
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fieldproject`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fieldproject`.`wishlist` (
  `wishlist_id` BIGINT NOT NULL AUTO_INCREMENT,
  `wishlist_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NULL DEFAULT NULL,
  `book_isbn` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`wishlist_id`),
  INDEX `wishlist_user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `wishlist_book_fk_idx` (`book_isbn` ASC) VISIBLE,
  CONSTRAINT `wishlist_book_fk`
    FOREIGN KEY (`book_isbn`)
    REFERENCES `fieldproject`.`book` (`book_isbn`),
  CONSTRAINT `wishlist_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `fieldproject`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
