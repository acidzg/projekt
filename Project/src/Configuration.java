
public class Configuration {
	
	private final InputConfiguration inConf;
	private final OutputConfiguration outConf;
	
	public Configuration()
	{
		inConf = new InputConfiguration();
		outConf = new OutputConfiguration();
	}
	
	public Configuration(String inFilePath, String outDatabaseLogin, String outDatabasePass, String outDatabaseAddress)
	{
		inConf = new InputConfiguration(inFilePath);
		outConf = new OutputConfiguration(outDatabaseLogin, outDatabasePass, outDatabaseAddress);
	}

}
