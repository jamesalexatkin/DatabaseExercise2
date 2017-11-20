import java.sql.*;
import java.util.Random;

public class Program {
	public static void main(String[] args){
		System.setProperty("jdbc.drivers", "org.postgresql.Driver");
		String dbName = "jdbc:postgresql://mod-intro-databases/jaa603";
		try {
			Connection dbConn = DriverManager.getConnection(dbName, "jaa603", "yIURvuspDV");
			System.out.println("worked");
			DatabaseHandler db = new DatabaseHandler(dbConn);
			db.createDatabase();
			generateJokes(db);
			generateGifts(db);
			generateHats(db);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateJokes(DatabaseHandler db) {
		Random r = new Random();
		int min = 0;
		int max = 10;
		for (int i = 0; i < 100; i++) {
			String joke = "Joke " + i;
			float royalty = min + r.nextFloat() * (max - min);
			db.insertJoke(i, joke, royalty);
		}
	}
	
	public static void generateGifts(DatabaseHandler db) {
		Random r = new Random();
		int min = 0;
		int max = 10;
		for (int i = 0; i < 100; i++) {
			String description = "Gift " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertGift(i, description, price);
		}
	}
	
	public static void generateHats(DatabaseHandler db) {
		Random r = new Random();
		int min = 0;
		int max = 10;
		for (int i = 0; i < 100; i++) {
			String description = "Hat " + i;
			float price = min + r.nextFloat() * (max - min);
			db.insertHat(i, description, price);
		}
	}
	
	public static void generateCrackers() {
		
	}
	
}
