package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String USERNAME = "postgres";
	private static final String PSSWORD = "postgres";
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/agenda";
	private static Connection connection = null;
	
	public static Connection createConnectionToPostgres() throws Exception {
		
		Class.forName("org.postgresql.Driver");
		
		connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PSSWORD);
		
		return connection;
	}
	
	public static boolean closeConnection() {
		
		boolean retorno = false;
		
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			retorno = true;
		}
		return retorno;
	}
	
	

}
