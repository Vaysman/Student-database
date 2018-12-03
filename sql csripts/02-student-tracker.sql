CREATE DATABASE  IF NOT EXISTS `web_student_tracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `web_student_tracker`;
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `student` WRITE;

INSERT INTO `student` VALUES (1,'Mary','Jay','maryJay@hotmail.com'),(2,'John','Deer','john@gmail.com'),(3,'Mark','Bengson','markbengson@mail.com'),(4,'Billy','Geer','bill@yahoo.com'),(5,'Orvel','Jogging','jogging@orvel.com'),(6,'Margaret','Blum','marja@hotmail.com'),(7,'Tonny','Blame','blametonny@gmail.com'),(8,'Martin','Scorcese','marik@mail.com'),(9,'Billy','Gee','billG@yahoo.com'),(10,'Marcy','Cry','marycay@hotmail.com'),(11,'John','Deph','johnd@gmail.com'),(12,'Folson','Bruks','markbruks@mail.com'),(13,'Billy','Murray','billMurray@yahoo.com');

UNLOCK TABLES;