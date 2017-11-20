import java.sql.*;

public class Program {
	public static void main(String[] args){
		System.setProperty("jdbc.drivers", "org.postgresql.Driver");
		String dbName = "jdbc:postgresql://mod-intro-databases/jaa603";
		try {
			Connection dbConn = DriverManager.getConnection(dbName, "jaa603", "yIURvuspDV");
			System.out.println("worked");
			DatabaseHandler db = new DatabaseHandler(dbConn);
			db.createDatabase();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
