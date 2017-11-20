--NEED TO CHECK THAT 'numeric' IS A GOOD DATATYPE FOR CURRENCY

--Cracker:
CREATE TABLE Cracker (
	cid integer PRIMARY KEY,
	name text NOT NULL,
	jid integer REFERENCES Joke (jid),
	gid integer REFERENCES Gift (gid),
	hid integer REFERENCES Hat (hid),
	saleprice decimal(4,2) NOT NULL CHECK (saleprice >= 0),
	quantity integer NOT NULL CHECK (quantity >= 0)
);

--Joke:
CREATE TABLE Joke (
	jid integer PRIMARY KEY,
	joke text NOT NULL,
	royalty decimal(4,2) NOT NULL CHECK (royalty >= 0)
);

--Gift:
CREATE TABLE Gift (
	gid integer PRIMARY KEY,
	description text NOT NULL,
	price decimal(4,2) NOT NULL CHECK (price >= 0)
);

--Hat:
CREATE TABLE Hat (
	hid integer PRIMARY KEY,
	description text NOT NULL,
	price decimal(4,2) NOT NULL CHECK (price >= 0)
);
