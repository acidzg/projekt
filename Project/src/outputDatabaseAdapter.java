public class outputDatabaseAdapter implements OutputAdapter {

	private final Configuration configuration;

	public outputDatabaseAdapter() {

	}

	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

}
