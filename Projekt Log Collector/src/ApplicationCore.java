import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationCore {
	private final String configurationFile = "configuration.txt";
	private Configuration configuration;
	private QueueManager queue;
	private InputFileAdapter inputFileAdapter;
	private OutputDatabaseAdapter outputDatabaseAdapter;

	public ApplicationCore() {
		configuration = readAndCreateConfiguration();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		queue = new QueueManager(outputDatabaseAdapter);
		queue.start();
		inputFileAdapter = new InputFileAdapter();
		inputFileAdapter.start();
		inputFileAdapter.connectToQueueManager(queue);
	} 

	private Configuration readAndCreateConfiguration() {
		Properties properties = readConfigurationFromFile();

		return createConfiguration(properties);
	}

	private Properties readConfigurationFromFile() {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("configuration.txt");

			properties.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	private Configuration createConfiguration(Properties properties) {
		Configuration configuration = new Configuration(
				properties.getProperty("inFilePath"),
				properties.getProperty("outDatabaseLogin"),
				properties.getProperty("outDatabasePass"),
				properties.getProperty("outDatabaseAddress"));

		return configuration;
	}
}
