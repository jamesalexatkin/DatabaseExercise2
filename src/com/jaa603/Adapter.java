package com.jaa603;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Adapter {

	protected Model model;
	
	public Adapter(Model model) {
		this.model = model;
	}

	public ResultSet getCracker(String cid) throws SQLException, NumberFormatException {
		return model.getCracker(cid);
	}

	public ResultSet getJoke(String jid) throws SQLException, NumberFormatException {
		return model.getJoke(jid);
	}

	public void addCracker(Object cid, Object name, Object jid, Object gid, Object hid, Object saleprice, int quantity) throws NumberFormatException, SQLException {
		model.addCracker(cid, name, jid, gid, hid, saleprice, quantity);
	}

	public void closeDbConnection() {
		model.closeDbConnection();
	}
}
