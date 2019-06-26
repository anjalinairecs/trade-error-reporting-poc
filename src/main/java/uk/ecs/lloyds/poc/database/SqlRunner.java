package uk.ecs.lloyds.poc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uk.ecs.lloyds.poc.exception.TradeErrorReportingException;

public class SqlRunner {

	public static ResultSet runSelectQuery(Connection connection, String query) throws TradeErrorReportingException{
		try {
			PreparedStatement prepStmt = connection.prepareStatement(query);
			return prepStmt.executeQuery();
		} catch (SQLException e) {
			throw new TradeErrorReportingException("Error while querying database : ", e);
		}
	}

}
