package com.jaa603;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Model {
	
	private Connection connection;
	private DatabaseHandler db;
	
	public Model() {
		// Gets the connection to the database
		connection = connectToDatabase();
		db = new DatabaseHandler(connection);
		// Drops all the tables so we can start with a fresh database
		db.dropDatabase();
		// Creates tables
		db.createDatabase();
		// Generates data to add to database
		// Jokes, gifts and hats must be made before crackers
		generateJokes(db, 0.01f, 1.5f);
		generateGifts(db, 0.01f, 1.5f);
		generateHats(db, 0.01f, 1.5f);
		generateCrackers(db, 1.5f, 4.0f);
		System.out.println("DB set up");
	}
	
	/**
	 * Establishes a connection to the database.
	 * @return the Connection
	 */
	private static Connection connectToDatabase() {
		System.setProperty("jdbc.drivers", "org.postgresql.Driver");
		String username = "jaa603";
		String password = "yIURvuspDV";
		String dbName = "jdbc:postgresql://mod-intro-databases/" + username;		
		Connection dbConn;
		try {
			dbConn = DriverManager.getConnection(dbName, username, password);
			return dbConn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error connecting to database.");
		}
		return null;
	}
	
	/**
	 * Generates 10 realistic crackers and 990 procedural crackers, and inserts each one to the database.
	 * @param db
	 * @param min
	 * @param max
	 */
	private static void generateCrackers(DatabaseHandler db, float min, float max) {
		// First, generate 10 realistic crackers
		// 10 cracker names
		String[] names = new String[]{
			"Sainsbury's Taste the Difference Cracker",
			"Christmas Cracker",
			"Cracker for Any Occasion",
			"Bad Cracker",
			"Corporate Cracker",
			"Aldi Everyday Essentials Cracker",
			"Happy Cracker",
			"New Year's Cracker",
			"Easter Cracker",
			"Cracker for a Misspent Youth"
		};
		// 10 joke IDs
		int[] jids = new int[]{
			0, 1, 2, 3, 4, 5, 6, 7, 8, 9	
		};
		// 10 gift IDs
		int[] gids = new int[]{
			9, 8, 7, 6, 5, 4, 3, 2, 1, 0
		};
		// 10 hat IDs
		int[] hids = new int[]{
			5, 9, 0, 6, 7, 8, 1, 2, 3, 4
		};
		// 10 sale prices
		float[] saleprices = new float[]{
			2.50f, 2.34f, 3.34f, 5.23f, 1.67f, 2.78f, 4.45f, 7.19f, 5.92f, 6.83f
		};
		// 10 quantities
		int[] quantities = new int[]{
			56, 293, 2367, 5829, 10, 45, 314, 4500, 324, 710
		};
		// Insert crackers into database
		for (int i = 0; i < names.length; i++) {
			db.insertCracker(i, names[i], jids[i], gids[i], hids[i], saleprices[i], quantities[i]);
		}
		
		// Now generate the next 990 crackers randomly
		Random r = new Random();
		for (int i = 10; i < 1000; i++) {
			String name = "Cracker " + i;
			int jid = r.nextInt(100);
			int gid = r.nextInt(100);
			int hid = r.nextInt(100);
			float saleprice = min + r.nextFloat() * (max - min);
			int quantity = r.nextInt(1000);
			db.insertCracker(i, name, jid, gid, hid, saleprice, quantity);
		}
	}

	/**
	 * Generates 10 realistic jokes and 90 procedural jokes, and inserts each one to the database.
	 * @param db
	 * @param min
	 * @param max
	 */
	public static void generateJokes(DatabaseHandler db, float min, float max) {
		// First, generate 10 realistic jokes
		// 10 jokes
		String[] jokes = new String[]{
				"Can a kangaroo jump higher than a house? Of course, a house can’t jump at all.", 
				"My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.", 
				"If you spent your day in a well, can you say your day was well-spent?", 
				"I Googled 'how to start a wildfire'. I got 48,500 matches.", 
				"Why did the balloon go near the needle? It wanted to be a pop star.", 
				"Which country’s capital is the fastest growing? Ireland’s - Every year it’s Dublin.", 
				"Apparently taking a day off is not something you should do when you work for a calendar company.", 
				"What’s the biggest pan in the world? Japan.", 
				"Velcros are just a big rip-off.", 
				"How do you make holy water? Boil the hell out of it!"};
		// 10 royalty prices
		float[] royalties = new float[]{
				0.06f, 0.56f, 0.45f, 0.2f, 1.45f, 0.12f, 0.34f, 0.98f, 1.00f, 1.01f
		};
		// Insert jokes into database
		for (int i = 0; i < jokes.length; i++) {
			db.insertJoke(i, jokes[i], royalties[i]);
		}
		
		// Now generate the next 90 jokes randomly
		Random r = new Random();
		for (int i = 10; i < 100; i++) {
			String joke = "Joke " + i;
			float royalty = min + r.nextFloat() * (max - min);
			db.insertJoke(i, joke, royalty);
		}
	}
	
	/**
	 * Generates 10 realistic gifts and 90 procedural gifts, and inserts each one to the database.
	 * @param db
	 * @param min
	 * @param max
	 */
	public static void generateGifts(DatabaseHandler db, float min, float max) {
		// First, generate 10 realistic jokes
		// 10 gifts
		String[] descriptions = new String[]{
				"Wind-up toy car.", 
				"Small black comb.", 
				"Pack of cards.", 
				"Novelty paperclip.", 
				"5 assorted colouring pencils.", 
				"Miniature Old Joe figurine.", 
				"Plastic moustache.", 
				"Six-sided die.", 
				"Pair of scissors.", 
				"Wire egg cup."};
		// 10 prices
		float[] prices = new float[]{
				0.56f, 0.45f, 0.81f, 1.00f, 1.01f, 0.22f, 0.45f, 1.20f, 0.23f, 0.60f 
		};
		// Insert gifts into database
		for (int i = 0; i < descriptions.length; i++) {
			db.insertGift(i, descriptions[i], prices[i]);
		}
				
		// Now generate the next 90 gifts randomly
		Random r = new Random();
		for (int i = 10; i < 100; i++) {
			String description = "Gift " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertGift(i, description, price);
		}
	}
	
	/**
	 * Generates 10 realistic hats and 90 procedural hats, and inserts each one to the database.
	 * @param db
	 * @param min
	 * @param max
	 */
	public static void generateHats(DatabaseHandler db, float min, float max) {
		// First, generate 10 realistic hats
		// 10 hats
		String[] descriptions = new String[]{
				"Red paper hat.", 
				"Blue paper hat.", 
				"Green and orange paper hat.", 
				"Reflective silver paper hat.", 
				"Blue paper hat", 
				"Hat that expands in water.", 
				"Black and yellow paper hat.", 
				"Teal paper hat.", 
				"Turquoise paper hat.", 
				"Purple and mauve paper hat."};
		// 10 prices
		float[] prices = new float[]{
				0.81f, 1.80f, 0.32f, 0.46f, 0.10f, 0.52f, 0.45f, 1.02f, 0.63f, 0.05f
		};
		// Insert hats into database
		for (int i = 0; i < descriptions.length; i++) {
			db.insertHat(i, descriptions[i], prices[i]);
		}
				
		// Now generate the next 90 hats randomly		
		Random r = new Random();
		for (int i = 10; i < 100; i++) {
			String description = "Hat " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertHat(i, description, price);
		}
	}

	/**
	 * Gets a cracker from the database handler.
	 * @param cid
	 * @return a ResultSet containing any matching crackers
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ResultSet getCracker(String cid) throws SQLException, NumberFormatException {
		return db.selectCracker(cid);		
	}

	/**
	 * Gets a joke from the database handler.
	 * @param jid
	 * @return a ResultSet containing any matching crackers
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ResultSet getJoke(String jid) throws SQLException, NumberFormatException {
		return db.selectJoke(jid);	
	}

	/**
	 * Uses the database handler to add a new cracker to the database.
	 * @param cid
	 * @param name
	 * @param jid
	 * @param gid
	 * @param hid
	 * @param saleprice
	 * @param quantity
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void addCracker(Object cid, Object name, Object jid, Object gid, Object hid, Object saleprice, int quantity) throws SQLException, NumberFormatException {
		db.insertCracker(cid, name, jid, gid, hid, saleprice, quantity);
	}

	/**
	 * Closes the connection to the database.
	 */
	public void closeDbConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
