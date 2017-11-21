package com.jaa603;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Model {
	
	private Connection dbConn;
	private DatabaseHandler db;
	
	public Model() {
		dbConn = connectToDatabase();
		db = new DatabaseHandler(dbConn);
		db.dropDatabase();
		db.createDatabase();
		generateJokes(db);
		generateGifts(db);
		generateHats(db);
		generateCrackers(db);
		System.out.println("DB set up");
	}
	
	private static Connection connectToDatabase() {
		System.setProperty("jdbc.drivers", "org.postgresql.Driver");
		String dbName = "jdbc:postgresql://mod-intro-databases/jaa603";
		Connection dbConn;
		try {
			dbConn = DriverManager.getConnection(dbName, "jaa603", "yIURvuspDV");
			return dbConn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error connecting to database.");
		}
		return null;
	}
	
	private static void generateCrackers(DatabaseHandler db) {
		Random r = new Random();
		int min = 3;
		int max = 10;
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

	public static void generateJokes(DatabaseHandler db) {
		Random r = new Random();
		int min = 0;
		int max = 5;
		for (int i = 0; i < 100; i++) {
			String joke = "Joke " + i;
			float royalty = min + r.nextFloat() * (max - min);
			db.insertJoke(i, joke, royalty);
		}
	}
	
	public static void generateGifts(DatabaseHandler db) {
		Random r = new Random();
		int min = 0;
		int max = 5;
		for (int i = 0; i < 100; i++) {
			String description = "Gift " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertGift(i, description, price);
		}
	}
	
	public static void generateHats(DatabaseHandler db) {
		Random r = new Random();
		int min = 0;
		int max = 5;
		for (int i = 0; i < 100; i++) {
			String description = "Hat " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertHat(i, description, price);
		}
	}

	public ResultSet getCracker(String cid) throws SQLException, NumberFormatException {
		return db.selectCracker(cid);		
	}
}