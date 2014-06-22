import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class OutputDatabaseAdapter implements OutputAdapter {
	
	private Configuration configuration;
	private Connection connection = null;
	// /statement does not appear
	private Statement statement = null;
	// /wynik zapytania sqlQuery
	private ResultSet resultSet;
	// /query of sql
	
	public OutputDatabaseAdapter() {}
	
	public void setupConfig(Configuration config) {
		this.configuration = config; 
	}
	public boolean storeEvents(List<Event> batch) {
		// to do
		return false;
	}
	
	public void openConnection(){ //chwilowo public byle tylko sprawdzic conf z pliku
		try {
			Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(configuration.outConf.databaseAddress,
					configuration.outConf.databaseLogin, configuration.outConf.databasePass); 
//			connection = DriverManager.getConnection("jdbc:mysql://matuszak.net.pl",
//					"matuszak_abc", "1qaz2wsx"); 
			// getting connection to database
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("nawiazano polaczenie z baza danych");
	}
	private void closeConnection(){
		try {
			connection.close(); //closing a current connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String args[]){
//		OutputDatabaseAdapter adapter = new OutputDatabaseAdapter();
//		adapter.openConnection();
//	}
}