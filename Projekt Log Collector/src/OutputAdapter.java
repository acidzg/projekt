import java.util.List;

public abstract interface OutputAdapter {
	public abstract void setupConfig(Configuration paramConfiguration);

	public abstract boolean storeEvents(List<Event> paramList);
}
