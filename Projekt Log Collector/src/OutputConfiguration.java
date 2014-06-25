public class OutputConfiguration {
	public final String databaseLogin;
	public final String databasePass;
	public final String databaseAddress;

	public OutputConfiguration() {
		this.databaseLogin = "admin";
		this.databasePass = "h@rdp@ss";
		this.databaseAddress = "";
/**
 * konfiguracja danych wymaganych do zalogowania si na serwer.
 */
	}

	public OutputConfiguration(String databaseLogin, String databasePass, String databaseAddress) {
		this.databaseLogin = databaseLogin;
		this.databasePass = databasePass;
		this.databaseAddress = databaseAddress;
	}
}
