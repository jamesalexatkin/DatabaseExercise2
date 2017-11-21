package com.jaa603;
import java.awt.*;
import java.sql.*;
import java.util.Random;

import javax.swing.*;

public class Program {
	public static void main(String[] args){
//		Connection dbConn = connectToDatabase();
//		DatabaseHandler db = new DatabaseHandler(dbConn);
//		db.dropDatabase();
//		db.createDatabase();
//		generateJokes(db);
//		generateGifts(db);
//		generateHats(db);
//		generateCrackers(db);
//		showUI();
		Model model = new Model();
		Adapter adapter = new Adapter(model);
		View view = new View(adapter);
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
	
	public static void showUI() {
		JFrame frame = new JFrame("UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("label");
		label.setPreferredSize(new Dimension(500, 500));
		label.setBackground(new Color(0,255,0));
		label.setFont(new Font("Serif", Font.PLAIN, 40));
		label.setOpaque(true);
		frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);
	}
}
