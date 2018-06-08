DROP TABLE IF EXISTS users_groups;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Groups;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `groups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `users_groups` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  INDEX `groupId_idx` (`group_id` ASC),
  CONSTRAINT `userId`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `groupId`
    FOREIGN KEY (`group_id`)
    REFERENCES `groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
 INSERT INTO  `Users` VALUES (DEFAULT, 'ivanov');
 INSERT INTO  `Groups` VALUES (DEFAULT, 'teamA');