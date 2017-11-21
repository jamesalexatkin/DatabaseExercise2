-- Part 3.1
-- whole query
SELECT Cracker.cid, Cracker.name, Gift.description, Joke.joke, Hat.description, Cracker.saleprice, 
	(Joke.royalty + Gift.price + Hat.price) AS costprice, 
	Cracker.quantity, ((saleprice - (Joke.royalty + Gift.price + Hat.price)) * quantity) AS netprofit
FROM (((Cracker
INNER JOIN Joke ON Cracker.jid = Joke.jid)
INNER JOIN Gift ON Cracker.gid = Gift.gid)
INNER JOIN Hat ON Cracker.hid = Hat.hid)
WHERE Cracker.cid = ?;


-- query to get just the cost price
SELECT (Joke.royalty + Gift.price + Hat.price) AS costprice
FROM (((Cracker
INNER JOIN Joke ON Cracker.jid = Joke.jid)
INNER JOIN Gift ON Cracker.gid = Gift.gid)
INNER JOIN Hat ON Cracker.hid = Hat.hid)
WHERE Cracker.cid = 0;


-- Part 3.2
SELECT Joke.jid, Joke.joke, Joke.royalty, SUM(Cracker.quantity) AS numberofuses, SUM(Cracker.quantity * Joke.royalty) AS totalroyalty
FROM Joke
INNER JOIN Cracker ON Joke.jid = Cracker.jid
WHERE Joke.jid = ?
GROUP BY Joke.jid;


-- query to get just number of uses
SELECT SUM(Cracker.quantity) AS numberofuses
FROM Cracker
WHERE Cracker.jid = 0;

-- query to get just total royalty
SELECT SUM(Cracker.quantity * Joke.royalty) AS totalroyalty
FROM Joke
INNER JOIN Cracker ON Joke.jid = Cracker.jid
WHERE Joke.jid = 0;
