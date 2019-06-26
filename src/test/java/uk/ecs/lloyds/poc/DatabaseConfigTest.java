package uk.ecs.lloyds.poc;

import org.junit.Test;

import uk.ecs.lloyds.poc.database.DatabaseConfig;
import uk.ecs.lloyds.poc.database.DatabaseType;
import uk.ecs.lloyds.poc.database.DatabaseUtil;
import uk.ecs.lloyds.poc.database.SqlRunner;
import uk.ecs.lloyds.poc.exception.TradeErrorReportingException;
import uk.ecs.lloyds.poc.file.ExcelUtil;

public class DatabaseConfigTest {

	DatabaseConfig config = new DatabaseConfig(DatabaseType.POSTGRES, "localhost", 5432, "anjali", "anjali", "anjali");

	
	@Test
	public void testAll() throws TradeErrorReportingException{
		ExcelUtil.write(SqlRunner.runSelectQuery(DatabaseUtil.getConnection(config), "select * from jobs.jobs order by id"), "test.xslx");
	}
}
