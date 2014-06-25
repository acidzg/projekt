public class InputConfiguration
{
  public final String FilePath;

  public InputConfiguration()
  {
    FilePath = "logs.txt";
    /**
     *sciezka pliku z logami
     */
  }

  public InputConfiguration(String FilePath)
  {
    this.FilePath = FilePath;
  }
}
