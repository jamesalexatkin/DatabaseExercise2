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
}
