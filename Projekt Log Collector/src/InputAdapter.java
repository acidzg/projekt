public abstract interface InputAdapter
{
  public abstract void setupConfig(Configuration configuration);
  /**
   *konfiguracja
   */

  public abstract void connectToQueueManager(QueueManager queueManager);
}
/**podlaczanie sie do menadzera kolejki */
