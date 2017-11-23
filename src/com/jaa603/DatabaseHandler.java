package com.jaa603;
import java.sql.*;

public class DatabaseHandler {
    // PreparedStatements
    private PreparedStatement insertJokeStatement;
    private PreparedStatement insertGiftStatement;
    private PreparedStatement insertHatStatement;
    private PreparedStatement insertCrackerStatement;
    
    private PreparedStatement selectCrackerStatement;
    private PreparedStatement selectJokeStatement;
    
    // Connection for the database
	private Connection connection;
	
   
	// Constructor
    public DatabaseHandler(Connection connection) {
		this.connection = connection;
		try {
			initStatements();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * Prepares all the PreparedStatements that will be used.
     * @throws SQLException
     */
	private void initStatements() throws SQLException {
		// Statement for inserting a joke
		String insertJokeString = "INSERT INTO Joke (jid, joke, royalty) VALUES (?, ?, ?);";
	    insertJokeStatement = connection.prepareStatement(insertJokeString);
	    
	    // Statement for inserting a gift
	    String insertGiftString = "INSERT INTO Gift (gid, description, price) VALUES (?, ?, ?);";
	    insertGiftStatement = connection.prepareStatement(insertGiftString);
	    
	    // Statement for inserting a hat
	    String insertHatString = "INSERT INTO Hat (hid, description, price) VALUES (?, ?, ?);";
	    insertHatStatement = connection.prepareStatement(insertHatString);
	    
	    // Statement for inserting a cracker
	    String insertCrackerString = "INSERT INTO Cracker (cid, name, jid, gid, hid, saleprice, quantity) VALUES (?, ?, ?, ?, ?, ?, ?);";
	    insertCrackerStatement = connection.prepareStatement(insertCrackerString);
	    
	    // Statement for selecting a cracker (Part 3.1)
	    String selectCrackerString = "SELECT Cracker.cid, Cracker.name, Gift.description, Joke.joke, Hat.description, Cracker.saleprice, (Joke.royalty + Gift.price + Hat.price) AS costprice, Cracker.quantity, ((saleprice - (Joke.royalty + Gift.price + Hat.price)) * quantity) AS netprofit"
	    		+ " FROM (((Cracker"
	    		+ " INNER JOIN Joke ON Cracker.jid = Joke.jid)"
	    		+ " INNER JOIN Gift ON Cracker.gid = Gift.gid)"
	    		+ " INNER JOIN Hat ON Cracker.hid = Hat.hid)"
	    		+ " WHERE Cracker.cid = ?;";
	    selectCrackerStatement = connection.prepareStatement(selectCrackerString);
	
	    // Statement for selecting a joke (Part 3.2)
	    String selectJokeString = "SELECT Joke.jid, Joke.joke, Joke.royalty, SUM(Cracker.quantity) AS numberofuses, SUM(Cracker.quantity * Joke.royalty) AS totalroyalty"
			+ " FROM Joke"
			+ " INNER JOIN Cracker ON Joke.jid = Cracker.jid"
			+ " WHERE Joke.jid = ?"
			+ " GROUP BY Joke.jid;";
	    selectJokeStatement = connection.prepareStatement(selectJokeString);
	}

	/**
	 * Creates the four tables needed for the database.
	 */
	public void createDatabase() {
		// Create joke table
		String sql = "CREATE TABLE Joke ("
				+ "jid integer PRIMARY KEY, "
				+ "joke text NOT NULL, " 
				+ "royalty decimal(4,2) NOT NULL CHECK (royalty >= 0)" 
				+ ");";
		createTable(sql, "Joke");
		
		// Create gift table
		sql = "CREATE TABLE Gift ( " 
				+ "gid integer PRIMARY KEY, " 
				+ "description text NOT NULL, " 
				+ "price decimal(4,2) NOT NULL CHECK (price >= 0)" 
				+ ");";
		createTable(sql, "Gift");
		
		// Create hat table
		sql = "CREATE TABLE Hat ( " 
				+ "hid integer PRIMARY KEY, " 
				+ "description text NOT NULL, " 
				+ "price decimal(4,2) NOT NULL CHECK (price >= 0)" 
				+ ");";
		createTable(sql, "Hat");
		
		// Create cracker table
        sql = "CREATE TABLE Cracker ( " 
		+ "cid integer PRIMARY KEY, " 
        		+ "name text NOT NULL, " 
        		+ "jid integer REFERENCES Joke(jid) NOT NULL, " 
        		+ "gid integer REFERENCES Gift(gid) NOT NULL, " 
        		+ "hid integer REFERENCES Hat(hid), " 
        		+ "saleprice decimal(4,2) NOT NULL CHECK (saleprice >= 0), " 
        		+ "quantity integer NOT NULL CHECK (quantity >= 0)" 
        		+ ");";   
        createTable(sql, "Cracker");
    }

	/**
	 * Drops the four tables of the databse.
	 */
	public void dropDatabase() {		
		dropTable("Cracker");
		dropTable("Joke");
		dropTable("Gift");
		dropTable("Hat");
	}
	
	/**
	 * Drops the table with the specified name.
	 * @param tableName
	 */
	private void dropTable(String tableName) {
		String sql = "DROP TABLE " + tableName + ";";
		try {
			PreparedStatement dropStatement = connection.prepareStatement(sql);
			dropStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error dropping table '" + tableName + "'. Check that it exists in the database.");
		}
	}

	/**
	 * Creates a table with the specified name.
	 * @param sql
	 * @param tableName
	 */
	private void createTable(String sql, String tableName) {
		try {
			PreparedStatement createTableStatement = connection.prepareStatement(sql);		
			createTableStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating table '" + tableName + "'.");
		}		
	}

	/**
	 * Inserts a new joke with the specified details.
	 * @param jid
	 * @param joke
	 * @param royalty
	 */
	public void insertJoke(int jid, String joke, float royalty) {
		try {
			insertJokeStatement.setInt(1, jid);
			insertJokeStatement.setString(2, joke);
			insertJokeStatement.setFloat(3, royalty);
			insertJokeStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error inserting joke.");
		}
	}

	/**
	 * Inserts a new gift with the specified details.
	 * @param gid
	 * @param description
	 * @param price
	 */
	public void insertGift(int gid, String description, float price) {
		try {
			insertGiftStatement.setInt(1, gid);
			insertGiftStatement.setString(2, description);
			insertGiftStatement.setFloat(3, price);
			insertGiftStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error inserting gift.");
		}		
	}

	/**
	 * Inserts a new hat with the specified details.
	 * @param hid
	 * @param description
	 * @param price
	 */
	public void insertHat(int hid, String description, float price) {
		try {
			insertHatStatement.setInt(1, hid);
			insertHatStatement.setString(2, description);
			insertHatStatement.setFloat(3, price);
			insertHatStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error inserting hat.");
		}	
	}

	/**
	 * Inserts a new cracker with the specified details.
	 * @param cid
	 * @param name
	 * @param jid
	 * @param gid
	 * @param hid
	 * @param saleprice
	 * @param quantity
	 */
	public void insertCracker(int cid, String name, int jid, int gid, int hid, float saleprice, int quantity) {
		try {
			insertCrackerStatement.setInt(1, cid);
			insertCrackerStatement.setString(2, name);
			insertCrackerStatement.setInt(3, jid);
			insertCrackerStatement.setInt(4, gid);
			insertCrackerStatement.setInt(5, hid);
			insertCrackerStatement.setFloat(6, saleprice);
			insertCrackerStatement.setInt(7, quantity);
			insertCrackerStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error inserting cracker.");
		}	
	}

	/**
	 * Selects the cracker with the specified cid.
	 * @param cid
	 * @return a ResultSet with any matching crackers
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ResultSet selectCracker(String cid) throws SQLException, NumberFormatException {
		int cidInt = Integer.parseInt(cid);
		selectCrackerStatement.setInt(1, cidInt);
		return selectCrackerStatement.executeQuery();
	}

	/**
	 * Selects the joke with the specified jid.
	 * @param jid
	 * @return a ResultSet with any matching jokes
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ResultSet selectJoke(String jid) throws SQLException, NumberFormatException {
		int jidInt = Integer.parseInt(jid);
		selectJokeStatement.setInt(1, jidInt);
		return selectJokeStatement.executeQuery();
	}

	/**
	 * Inserts a cracker with the specified details.
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
	public void insertCracker(Object cid, Object name, Object jid, Object gid, Object hid, Object saleprice,
			int quantity) throws SQLException, NumberFormatException {
			insertCrackerStatement.setInt(1, Integer.parseInt((String) cid));
			insertCrackerStatement.setString(2, (String) name);
			insertCrackerStatement.setInt(3, Integer.parseInt((String) jid));
			insertCrackerStatement.setInt(4, Integer.parseInt((String) gid));
			insertCrackerStatement.setInt(5, Integer.parseInt((String) hid));
			insertCrackerStatement.setFloat(6, Float.parseFloat((String) saleprice));
			insertCrackerStatement.setInt(7, quantity);
			insertCrackerStatement.executeUpdate();
	}

	
}