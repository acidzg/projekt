public class OutputConfiguration {
	public final String databaseLogin;
	public final String databasePass;
	public final String databaseAddress;
    /**
     *konfiguracja wyjœciowa, wymagane login i haslo
     */
	public OutputConfiguration() {
		this.databaseLogin = "admin";
		this.databasePass = "h@rdp@ss";
		this.databaseAddress = "";
	}

	public OutputConfiguration(String databaseLogin, String databasePass, String databaseAddress) {
		this.databaseLogin = databaseLogin;
		this.databasePass = databasePass;
		this.databaseAddress = databaseAddress;
	}
}
