create schema if not exists acc_statistics default charset = UTF8;

use statistics;

# Header for all accommodations
 CREATE TABLE `stat_header` (
  `id` varbinary(16) NOT NULL,
  `_efk_testee` bigint(20) NOT NULL,
  `date` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
#Accommodation Video ASL
 CREATE TABLE `acc_video_asl` (
  `id` varbinary(16) NOT NULL,
  `fk_stat_header` varbinary(16) NOT NULL,
  `filename` varchar(80) DEFAULT NULL,
  `time_completion` float DEFAULT NULL,
  `replayed` bit(1) DEFAULT b'0',
  `seeked` bit(1) DEFAULT b'0',
  `when_played` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_header_idx` (`fk_stat_header`),
  CONSTRAINT `fk_stat_header_acc_asl` FOREIGN KEY (`fk_stat_header`) REFERENCES `stat_header` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#Accommodation glossary
 
 CREATE TABLE `acc_glossary` (
  `id` varbinary(16) NOT NULL,
  `fk_stat_header` varbinary(16) NOT NULL,
  `type_glossary` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_header_id_idx` (`fk_stat_header`),
  CONSTRAINT `fk_header_acc_glossary` FOREIGN KEY (`fk_stat_header`) REFERENCES `stat_header` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `glossary_terms` (
  `fk_acc_glossary` varbinary(16) NOT NULL,
  `item` varchar(45) NOT NULL,
  `term` varchar(45) NOT NULL,
  `invoke_counter` int(11) NOT NULL,
  `when_invoked` datetime(3) NOT NULL,
  `audio_play_counter` int(11) NOT NULL,
  KEY `fk_acc_glossary_id_idx` (`fk_acc_glossary`),
  CONSTRAINT `fk_acc_glossary_gloss_term` FOREIGN KEY (`fk_acc_glossary`) REFERENCES `acc_glossary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
 #Accommodation Braille embossed

CREATE TABLE `acc_braille_embossed` (
  `id` varbinary(16) NOT NULL,
  `fk_stat_header` varbinary(16) NOT NULL,
  `embossed_file_name` varchar(100) NOT NULL,
  `when_embossed` datetime(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_header_id_idx` (`fk_stat_header`),
  CONSTRAINT `fk_header_braille_embossed` FOREIGN KEY (`fk_stat_header`) REFERENCES `stat_header` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



