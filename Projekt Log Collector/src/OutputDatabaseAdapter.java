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

	public OutputDatabaseAdapter() {
	}

	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

	public boolean storeEvents(List<Event> batch) {
		boolean flag = false;
		if (openConnection()) {
			for (Event event : batch) {
				flag = statement.execute("INSERT INTO Events VALUES ("
						+ event.timestamp + ", " + event.loglevel + ", "
						+ event.details + ");");
				if (!flag)
					break;
			}
		}
		return flag;
	}

	private boolean openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // loading mysql driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
					configuration.outConf.databaseAddress,
					configuration.outConf.databaseLogin,
					configuration.outConf.databasePass);
			statement = connection.createStatement();
			System.out.println("nawiazano polaczenie z baza danych");
			return true;
			// connection =
			// DriverManager.getConnection("jdbc:mysql://matuszak.net.pl",
			// "matuszak_abc", "1qaz2wsx");
			// getting connection to database
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void closeConnection() {
		try {
			connection.close(); // closing a current connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// public static void main(String args[]){
	// OutputDatabaseAdapter adapter = new OutputDatabaseAdapter();
	// adapter.openConnection();
	// }
}