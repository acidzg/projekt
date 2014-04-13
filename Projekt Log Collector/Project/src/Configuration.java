public class Configuration
{
  public InputConfiguration inConf;
  public OutputConfiguration outConf;
  
  public Configuration()
  {
    this.inConf = new InputConfiguration();
    this.outConf = new OutputConfiguration();
  }
  
  public Configuration(String inFilePath, String outDatabaseLogin, String outDatabasePass, String outDatabaseAddress)
  {
    this.inConf = new InputConfiguration(inFilePath);
    this.outConf = new OutputConfiguration(outDatabaseLogin, outDatabasePass, outDatabaseAddress);
  }
}
