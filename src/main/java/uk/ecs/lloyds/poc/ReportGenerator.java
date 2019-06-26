package uk.ecs.lloyds.poc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import uk.ecs.lloyds.poc.database.DatabaseConfig;
import uk.ecs.lloyds.poc.database.DatabaseUtil;
import uk.ecs.lloyds.poc.database.SqlRunner;
import uk.ecs.lloyds.poc.exception.TradeErrorReportingException;
import uk.ecs.lloyds.poc.file.ExcelUtil;


public class ReportGenerator {

	protected static String jobs(DatabaseConfig databaseConfig) throws TradeErrorReportingException {
		String fileName = "ErroredJobs.xlsx";
		runQueryAndWriteToExcel(databaseConfig, "select * from trades.jobs order by id", fileName);
		return fileName;
	}

	private static void runQueryAndWriteToExcel(DatabaseConfig databaseConfig, String query, String fileName)
			throws TradeErrorReportingException {
		Connection connection = null;
		ResultSet resultset = null;
		connection = DatabaseUtil.getConnection(databaseConfig);
		resultset = SqlRunner.runSelectQuery(connection, query);
		ExcelUtil.write(resultset, fileName);

		try {
			if (null != resultset)
				resultset.close();
			if (null != connection)
				connection.close();
		} catch (SQLException e) {
			throw new TradeErrorReportingException("Error while closing resources : ", e);
		}
	}
}
