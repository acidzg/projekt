import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;

public class Event {

	private final Timestamp timestamp;
	private final Enumeration<Level> logLevel;
	private final String details;

	Event(Timestamp timestamp, Enumeration<Level> logLevel, String details) {
		this.timestamp = timestamp;
		this.logLevel = logLevel;
		this.details = details;
	}

}
