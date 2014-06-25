import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JTextArea;

public class ApplicationCore {
    /**Rdzeñ aplikacji
     */
	private Configuration configuration;
	private QueueManager queue;
	private OutputDatabaseAdapter outputDatabaseAdapter;
	InputFileAdapter inputFileAdapter;
    /**
     *adapter plików wejœciowych
     *adapter plków bazy danych wyjœciowych
     *menad¿er kolejki
     *konfiguracja
     */

	public ApplicationCore(JTextArea guiConsole) {
		configuration = readAndCreateConfiguration();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		queue = new QueueManager(outputDatabaseAdapter);
		queue.start();
	    /**tworzenie nowej kolejki **/
		inputFileAdapter = new InputFileAdapter(guiConsole);
		inputFileAdapter.start();
		inputFileAdapter.connectToQueueManager(queue);
		outputDatabaseAdapter.setupConfig(configuration);
		inputFileAdapter.setupConfig(configuration);
	}

	public void stopApp() {
	    /**funkcja sluzaca to zatrzymywania 	     */
		outputDatabaseAdapter = null;
		queue.stop();
		queue = null;
		inputFileAdapter.stop();
		inputFileAdapter = null;
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
