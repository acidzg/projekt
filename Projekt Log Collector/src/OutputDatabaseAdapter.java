import java.util.List;

public class OutputDatabaseAdapter implements OutputAdapter {
	private Configuration configuration;

	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

	public boolean storeEvents(List<Event> batch) {
		// to do
		return false;
	}
}
