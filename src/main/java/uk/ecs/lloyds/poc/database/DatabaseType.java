package uk.ecs.lloyds.poc.database;

public enum DatabaseType {
	POSTGRES("jdbc:postgresql");
	
	private String connectionPrefix;
	
	DatabaseType(String connectionPrefix){
		this.connectionPrefix = connectionPrefix;
	}
	protected String getConnectionPrefix() {
		return connectionPrefix;
	}
	
}
