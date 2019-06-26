package uk.ecs.lloyds.poc;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import uk.ecs.lloyds.poc.database.DatabaseConfig;
import uk.ecs.lloyds.poc.database.DatabaseType;
import uk.ecs.lloyds.poc.exception.TradeErrorReportingException;

public class Main {

	@Option(name = "-db", aliases="--database", usage = "Type of the database. Allowed values : \npostgres\noracle\n", required = true)
	private static String databaseType;

	@Option(name = "-h", aliases="--hostname", usage = "Database server name", required = true)
	private String hostName;

	@Option(name = "-p", aliases="--port", usage = "Port number used to connect to the database", required = false)
	private static int portNumber;

	@Option(name = "-s", aliases="--schema", usage = "Schema id to connect to", required = true)
	private static String schemaId;

	@Option(name = "-u", aliases="--user", usage = "Username to connect to the database", required = true)
	private static String username;

	@Option(name = "-pass", aliases="--password", usage = "Password for username", required = true)
	private static String password;

	@Option(name = "-o", aliases="--option", usage = "Reporting Options. Allowed values : \njobs", required = true)
	private static String report;

	public static void main(String[] args) throws TradeErrorReportingException {
		final Main instance = new Main();
		DatabaseConfig databaseConfig = instance.getDatabaseConfig(args);
		
		switch(report) {
		case "jobs"  :
			String file = ReportGenerator.jobs(databaseConfig);
			System.out.println("Report generated : " + file);
			break;
		}
	}
	
	private DatabaseConfig getDatabaseConfig(String[] args) throws TradeErrorReportingException {
		final CmdLineParser parser = new CmdLineParser(this);
		if (args.length < 1) {
			parser.printUsage(System.out);
			System.exit(-1);
		}
		try {
			parser.parseArgument(args);
			DatabaseConfig databaseConfig = new DatabaseConfig(DatabaseType.POSTGRES, hostName, portNumber,
					schemaId, username, password);
			return databaseConfig;
		} catch (CmdLineException e) {
			throw new TradeErrorReportingException("ERROR: Unable to parse command-line options: ",e);
		}  
	}
	
	

}
