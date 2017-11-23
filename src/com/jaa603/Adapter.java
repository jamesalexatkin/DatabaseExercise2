package com.jaa603;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Adapter {

	protected Model model;
	
	public Adapter(Model model) {
		this.model = model;
	}

	/**
	 * Gets a cracker from the model.
	 * @param cid
	 * @return the cracker
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ResultSet getCracker(String cid) throws SQLException, NumberFormatException {
		return model.getCracker(cid);
	}

	/**
	 * Gets a joke from the model.
	 * @param jid
	 * @return the joke
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public ResultSet getJoke(String jid) throws SQLException, NumberFormatException {
		return model.getJoke(jid);
	}

	/**
	 * Uses the model to insert a new cracker into the database.
	 * @param cid
	 * @param name
	 * @param jid
	 * @param gid
	 * @param hid
	 * @param saleprice
	 * @param quantity
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void addCracker(Object cid, Object name, Object jid, Object gid, Object hid, Object saleprice, int quantity) throws NumberFormatException, SQLException {
		model.addCracker(cid, name, jid, gid, hid, saleprice, quantity);
	}

	/**
	 * Closes the database connection in the model.
	 */
	public void closeDbConnection() {
		model.closeDbConnection();
	}
}
