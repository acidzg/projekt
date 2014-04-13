
public class OutputConfiguration {
	
	private final String databaseLogin;
	private final String databasePass;
	private final String databaseAddress;
	
	public OutputConfiguration()
	{
		databaseLogin = "admin";
		databasePass = "h@rdp@ss";
		databaseAddress = "";
	}
	
	public OutputConfiguration(String databaseLogin, String databasePass, String databaseAddress)
	{
		this.databaseLogin = databaseLogin;
		this.databasePass = databasePass;
		this.databaseAddress = databaseAddress;
	}

}
