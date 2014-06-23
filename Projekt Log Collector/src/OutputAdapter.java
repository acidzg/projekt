import java.util.List;

public abstract interface OutputAdapter {
	public abstract void setupConfig(Configuration configuration);
	
	public abstract boolean openConnection();
	public abstract boolean storeEvents(List<Event> list);
	public abstract void closeConnection();
}
