import java.util.List;
/**
 *adapter wyjœciowy
 */
public abstract interface OutputAdapter {
	public abstract void setupConfig(Configuration configuration);
	public abstract boolean storeEvents(List<Event> list);
}
