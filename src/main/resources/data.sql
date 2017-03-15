CREATE TABLE `users` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `account_non_expired` tinyint(1) NOT NULL DEFAULT '1',
  `credentials_non_expired` tinyint(1) NOT NULL DEFAULT '1',
  `account_non_locked` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`username`, `password`, `enabled`)
VALUES
	('ievgenii', '21111991', 1);

INSERT INTO `roles` (`username`, `role`)
VALUES
	('ievgenii', 'ROLE_USER'),
	('ievgenii', 'ROLE_ADMIN');

commit;