CREATE TABLE house(
	houseId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	eircode varchar(11) NOT NULL UNIQUE,
	address varchar(50) NOT NULL
);

CREATE TABLE occupants(
	occupantId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(30) NOT NULL,
	age int(11),
	occupation varchar(30) NOT NULL,
	eircode varchar(11),
	FOREIGN KEY (eircode) REFERENCES house(eircode) ON DELETE CASCADE
);