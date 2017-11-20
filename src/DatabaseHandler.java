import java.sql.*;

public class DatabaseHandler {
    // First, set up the table and column names
    // Store as constants to eliminate misspelling
    // Table names
    private static final String TABLE_CRACKER = "Cracker";
    private static final String TABLE_JOKE = "Joke";
    private static final String TABLE_GIFT = "Gift";
    private static final String TABLE_HAT = "Hat";
   
    // Column names
    private static final String COL_CRACKER_CID = "cid";
    private static final String COL_CRACKER_NAME = "name";
    private static final String COL_CRACKER_JID = "jid";
    private static final String COL_CRACKER_GID = "gid";
    private static final String COL_CRACKER_HID = "hid";
    private static final String COL_CRACKER_SALEPRICE = "saleprice";
    private static final String COL_CRACKER_QUANTITY = "quantity";
   
    private static final String COL_JOKE_JID = "jid";
    private static final String COL_JOKE_JOKE = "joke";
    private static final String COL_JOKE_ROYALTY = "royalty";
   
    private static final String COL_GIFT_GID = "gid";
    private static final String COL_GIFT_DESCRIPTION = "description";
    private static final String COL_GIFT_PRICE = "price";
   
    private static final String COL_HAT_HID = "hid";
    private static final String COL_HAT_DESCRIPTION = "description";
    private static final String COL_HAT_PRICE = "price";
    
    // Connection for the database
	private Connection connection;
   
	// Constructor
    public DatabaseHandler(Connection connection) {
		this.connection = connection;
	}

	public void createDatabase() {
		// Create joke table
		String sql = "CREATE TABLE " + TABLE_JOKE + " ( " +
				COL_JOKE_JID + " integer PRIMARY KEY, " +
				COL_JOKE_JOKE + " text NOT NULL, " +
				COL_JOKE_ROYALTY + " decimal(4,2) NOT NULL CHECK (" + COL_JOKE_ROYALTY + " >= 0)" +
				");";
		createTable(sql, TABLE_JOKE);
		
		// Create gift table
		sql = "CREATE TABLE " + TABLE_GIFT + " ( " +
				COL_GIFT_GID + " integer PRIMARY KEY, " +
				COL_GIFT_DESCRIPTION + " text NOT NULL, " +
				COL_GIFT_PRICE + " decimal(4,2) NOT NULL CHECK (" + COL_GIFT_PRICE + " >= 0)" +
				");";
		createTable(sql, TABLE_GIFT);
		
		// Create hat table
		sql = "CREATE TABLE " + TABLE_HAT + " ( " +
				COL_HAT_HID + " integer PRIMARY KEY, " +
				COL_HAT_DESCRIPTION + " text NOT NULL, " +
				COL_HAT_PRICE + " decimal(4,2) NOT NULL CHECK (" + COL_HAT_PRICE + " >= 0)" +
				");";
		createTable(sql, TABLE_HAT);
		
		// Create cracker table
        sql = "CREATE TABLE " + TABLE_CRACKER + " ( " +
                COL_CRACKER_CID + " integer PRIMARY KEY, " +
                COL_CRACKER_NAME + " text NOT NULL, " + 
                COL_CRACKER_JID + " integer REFERENCES " + TABLE_JOKE + " (" + COL_JOKE_JID + "), " +
                COL_CRACKER_GID + " integer REFERENCES " + TABLE_GIFT + " (" + COL_GIFT_GID + "), " +
                COL_CRACKER_HID + " integer REFERENCES " + TABLE_HAT + " (" + COL_HAT_HID + "), " +
                COL_CRACKER_SALEPRICE + " decimal(4,2) NOT NULL CHECK (" + COL_CRACKER_SALEPRICE + " >= 0), " + 
                COL_CRACKER_QUANTITY + " integer NOT NULL CHECK (" + COL_CRACKER_QUANTITY + " >= 0)" +
                ");";      
        createTable(sql, TABLE_CRACKER);
    }

	private void createTable(String sql, String tableName) {
		try {
			PreparedStatement create = connection.prepareStatement(sql);		
			create.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating table '" + tableName + "'.");
		}		
	}
}