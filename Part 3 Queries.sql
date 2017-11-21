-- Part 3.1
-- whole query
SELECT Cracker.cid, Cracker.name, Gift.description, Joke.joke, Hat.description, Cracker.saleprice, 
	SUM(Joke.royalty, Gift.price, Hat.price) AS costprice, 
	Cracker.quantity, ((saleprice-costprice)*quantity) AS netprofit
FROM (((Cracker
INNER JOIN Joke ON Cracker.jid = Joke.jid)
INNER JOIN Gift ON Cracker.gid = Gift.gid)
INNER JOIN Hat ON Cracker.hid = Hat.hid)
WHERE Cracker.cid = ?


-- query to get just the cost price
SELECT SUM(Joke.royalty, Gift.price, Hat.price) AS costprice
