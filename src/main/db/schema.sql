CREATE TABLE users
(
	email VARCHAR(30) NOT NULL PRIMARY KEY,
	password VARCHAR(30) NOT NULL,
	name VARCHAR(30) NOT NULL,
	lastName VARCHAR(30) NOT NULL,
	dateOfBirth date NOT NULL,
	jmbg VARCHAR(13) NOT NULL,
    address VARCHAR(30) NOT NULL,
    phone VARCHAR(30) NOT NULL,
	dateOfRegistration VARCHAR(30) NOT NULL,
    role  VARCHAR(30) NOT NULL
);

CREATE TABLE vakcine
(
	naziv VARCHAR(30) NOT NULL PRIMARY KEY,
	kolicina VARCHAR(30) NOT NULL,
	proizvodjac VARCHAR(30) NOT NULL
);

CREATE TABLE proizvodjaci
(
	proizvodjac VARCHAR(30) NOT NULL PRIMARY KEY,
	drzavaProizvodnje VARCHAR(30) NOT NULL,
	vakcina VARCHAR(30) NOT NULL
);

CREATE TABLE vesti
(
	naziv VARCHAR(30) NOT NULL PRIMARY KEY,
	sadrzaj VARCHAR(255) NOT NULL,
	vremeObjavljivanja VARCHAR(100) NOT NULL
);

CREATE TABLE vesti_o_obolelima
(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
	oboleliUPoslednja24h VARCHAR(255) NOT NULL,
	testiraniUPoslednja24h VARCHAR(255) NOT NULL,
	ukupnoObolelihOdStarta VARCHAR(255) NOT NULL,
	brojHospitalizovanih VARCHAR(255) NOT NULL,
	pacijentiNaRespiratoru VARCHAR(255) NOT NULL,
	vremeObjavljivanja VARCHAR(255) NOT NULL
);
