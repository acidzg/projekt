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
	private String databaseURL;
	private String databaseUser;
	private String databasePassword;
	public OutputDatabaseAdapter(String url, String user, String password){
		this.databaseURL = url;
		this.databaseUser = user;
		this.databasePassword = password;
	}
	public void setupConfig(Configuration config) {
		this.configuration = config; 
	}
	public boolean storeEvents(List<Event> batch) {
		// to do
		return false;
	}
	
	private void openConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword); 
			// getting connection to database
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("udalo sie");
	}
	private void closeConnection(){
		try {
			connection.close(); //closing a current connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String args[]){
//		OutputDatabaseAdapter adapter = new OutputDatabaseAdapter("jdbc:mysql://mysql.cba.pl/projekt202_cba_pl", "acidzg","yamaha12");
//		adapter.openConnection();
//	}
}