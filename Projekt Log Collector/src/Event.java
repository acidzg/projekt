import java.sql.Timestamp;
import java.util.logging.Level;

public class Event {
	private final Timestamp timestamp;
	private final Level logLevel;
	private final String details;

	public Event() {
		this.timestamp = new Timestamp(0);
		this.logLevel = Level.WARNING;
		this.details = "Test event";
	}

	public Event(Timestamp timestamp, Level logLevel, String details) {
		this.timestamp = timestamp;
		this.logLevel = logLevel;
		this.details = details;
	}

	public boolean isEmpty() {
		if(timestamp.equals(new Timestamp(0))) return true;
		else return false;
	}

	public void showEvent() {
		System.out.println(timestamp+" "+logLevel+" "+details);
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public Level getLogLevel() {
		return logLevel;
	}

	public String getDetails() {
		return details;
	}
}
