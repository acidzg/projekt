public abstract interface InputAdapter
{
  public abstract void setupConfig(Configuration configuration);
  
  public abstract void connectToQueueManager(QueueManager queueManager);
}
