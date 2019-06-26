package uk.ecs.lloyds.poc.database;

public class DatabaseConfig {

	DatabaseType databaseType;
	String hostname, schemaId, username, password;
	int portNumber;

	public DatabaseConfig(DatabaseType databaseType, String hostname, int portNumber, String schemaId, String username,
			String password) {
		this.databaseType = databaseType;
		this.hostname = hostname;
		this.portNumber = portNumber;
		this.schemaId = schemaId;
		this.username = username;
		this.password = password;
	}

	protected DatabaseType getDatabaseType() {
		return databaseType;
	}

	protected String getHostname() {
		return hostname;
	}

	protected String getSchemaId() {
		return schemaId;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

	protected int getPortNumber() {
		return portNumber;
	}

	@Override
	public String toString() {
		return "DatabaseConfig [databaseType=" + databaseType + ", hostname=" + hostname + ", schemaId=" + schemaId
				+ ", username=" + username + ", portNumber=" + portNumber + "]";
	}
	
	

}
