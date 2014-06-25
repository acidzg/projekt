import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OutputDatabaseAdapter implements OutputAdapter {

    private Configuration configuration;
    private Connection connection = null;
    // /statement does not appear
    private Statement statement = null;

    public OutputDatabaseAdapter() {
    }

    public final void setupConfig(final Configuration config) {
        this.configuration = config;
    }

    public final synchronized boolean storeEvents(final List<Event> batch) {
        boolean flag = true;
        if (openConnection()) {
            for (Event event : batch) {
                try {
                    flag = statement.execute(
                    		"INSERT INTO project202idz.events("
                            + "timestamp, level, details) VALUES (\'"
                            + event.getTimestamp()
                            + "\', \'" + event.getLogLevel() + "\', \'"
                            + event.getDetails() + "\');");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (flag) {
                    break;
                }
            }
        }
        closeConnection();
        return flag;
    }
    /**
     *utworzenie polaczenia z baza
     */
    private boolean openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
            	    /**
            	     *uzywanie podanych w klasie OutputConfiguration danych logowania
            	     *@see OutputConfiguration.java
            	     */
                    configuration.outConf.databaseAddress,
                    configuration.outConf.databaseLogin,
                    configuration.outConf.databasePass);
            statement = connection.createStatement();
            System.out.println(
                    "nawiazano polaczenie z baza danych");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     *zamykanie polaczenia z baz¹
     */
    private void closeConnection() {
        try {
            connection.close(); // zamykanie obecnego polaczenia
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
