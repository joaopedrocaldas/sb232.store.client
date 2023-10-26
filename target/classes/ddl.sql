CREATE TABLE cliente (
  id varchar(40) NOT NULL ,
  name varchar(100) NOT NULL,
  data_nas datetime NOT NULL,
  cpf varchar(30) NOT NULL,
  email varchar(100) NOT NULL,
  hash varchar(64) NOT NULL,
  PRIMARY KEY (id)
);