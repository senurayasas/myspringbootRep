CREATE TABLE IF NOT EXISTS `customer_model` (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  age varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  version BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS customer_model_history (
   id BIGINT,
   name varchar(255) NULL,
   age varchar(255) NULL,
   email varchar(255) NULL,
   rev INTEGER,
   revtype TINYINT
);

CREATE TABLE IF NOT EXISTS revinfo (
    rev INTEGER AUTO_INCREMENT PRIMARY KEY,
    revtstmp BIGINT
);