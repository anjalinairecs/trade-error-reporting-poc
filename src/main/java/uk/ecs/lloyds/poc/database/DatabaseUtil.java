package uk.ecs.lloyds.poc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import uk.ecs.lloyds.poc.exception.TradeErrorReportingException;

public class DatabaseUtil {

	private static String getConnectionString(DatabaseConfig databaseConfig) {
		return new StringBuffer(databaseConfig.getDatabaseType().getConnectionPrefix()).append("://").append(databaseConfig.getHostname()).append(":")
				.append(databaseConfig.getPortNumber()).append("/").append(databaseConfig.getSchemaId()).toString();
	}

	public static Connection getConnection(DatabaseConfig databaseConfig) throws TradeErrorReportingException{
		try {
			return DriverManager.getConnection(getConnectionString(databaseConfig), databaseConfig.getUsername(),
					databaseConfig.getPassword());
		} catch (SQLException e) {
			throw new TradeErrorReportingException("Error while connecting to database : ", e);
		}
	}
}
