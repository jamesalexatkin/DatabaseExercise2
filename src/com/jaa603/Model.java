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
	
	/*
	 * Establishes a connection to the database.
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
	
	/*
	 * Generates a total of 1000 crackers and adds each one to the database.
	 */
	private static void generateCrackers(DatabaseHandler db, float min, float max) {
		// First, generate 10 realistic crackers
		
		
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			String name = "Cracker " + i;
			int jid = r.nextInt(100);
			int gid = r.nextInt(100);
			int hid = r.nextInt(100);
			float saleprice = min + r.nextFloat() * (max - min);
			int quantity = r.nextInt(1000);
			db.insertCracker(i, name, jid, gid, hid, saleprice, quantity);
		}
	}

	/*
	 * Generates 10 realistic jokes and 90 procedural jokes, and inserts each one to the database.
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
	
	public static void generateGifts(DatabaseHandler db, float min, float max) {
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			String description = "Gift " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertGift(i, description, price);
		}
	}
	
	public static void generateHats(DatabaseHandler db, float min, float max) {
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			String description = "Hat " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertHat(i, description, price);
		}
	}

	public ResultSet getCracker(String cid) throws SQLException, NumberFormatException {
		return db.selectCracker(cid);		
	}

	public ResultSet getJoke(String jid) throws SQLException, NumberFormatException {
		return db.selectJoke(jid);	
	}

	public void addCracker(Object cid, Object name, Object jid, Object gid, Object hid, Object saleprice, int quantity) throws SQLException, NumberFormatException {
		db.insertCracker(cid, name, jid, gid, hid, saleprice, quantity);
	}

	/*
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
