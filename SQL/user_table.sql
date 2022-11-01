use `myhome`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` VARCHAR(50) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `interestArea` (
  `user_id` VARCHAR(50) NOT NULL,
  `dongCode` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`user_id`, `dongCode`),
  INDEX `fk_user_has_dongcode_dongcode1_idx` (`dongCode` ASC) VISIBLE,
  INDEX `fk_user_has_dongcode_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_dongcode_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_dongcode_dongcode1`
    FOREIGN KEY (`dongCode`)
    REFERENCES `dongcode` (`dongCode`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
