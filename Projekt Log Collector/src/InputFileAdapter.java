import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.logging.Level;

public class InputFileAdapter extends Thread implements InputAdapter {
	private Configuration configuration;
	private QueueManager queue;

	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

	public void connectToQueueManager(QueueManager queue) {
		this.queue = queue;
	}

	private Event readFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(
					"server.log.1");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileInputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sleep(1000);

				// *********

				if (!line.isEmpty()) {
					String a = line;
					String regularExp = "(\\))(.+)";
					String date = a.replaceAll(regularExp, "$1");

					String regularExp2 = "(\\()(.+)(\\))";

					String date2 = date.replaceAll(regularExp2, "$2");

					String regularExp3 = "(.+)(T)(.+)";

					String yearMonthDay = date2.replaceAll(regularExp3, "$1");
					String hourMinuteSecMil = date2.replaceAll(regularExp3,"$3");

					String regularExp4 = "([0-9]+)(-)([0-9]+)(-)([0-9]+)";

					Integer year = Integer.parseInt(yearMonthDay.replaceAll(regularExp4, "$1"));
					Integer month = Integer.parseInt(yearMonthDay.replaceAll(regularExp4, "$3"));
					Integer day = Integer.parseInt(yearMonthDay.replaceAll(regularExp4, "$5"));

					String regularExp5 = "([0-9]+)(:)([0-9]+)(:)([0-9]+)(\\.)([0-9]+)";

					Integer hour = Integer.parseInt(hourMinuteSecMil.replaceAll(regularExp5, "$1"));
					Integer minute = Integer.parseInt(hourMinuteSecMil.replaceAll(regularExp5, "$3"));
					Integer sec = Integer.parseInt(hourMinuteSecMil.replaceAll(regularExp5, "$5"));
					Integer mil = Integer.parseInt(hourMinuteSecMil.replaceAll(regularExp5, "$7"));

					Timestamp timestamp = new Timestamp(year - 1900, month - 1,day, hour, minute, sec, mil);

					String regularExpLevel = "(.+)(INFO|WARNING|SEVERE|CONFIG|FINE|FINER|FINEST)(.+)";
					String level = line.replaceAll(regularExpLevel, "$2");

					Level realLevel = Level.parse(level);
					
					String regularExpDetails = "(.+)(INFO|WARNING|SEVERE|CONFIG|FINE|FINER|FINEST)(.+)";
					String details = line.replaceAll(regularExpDetails, "$3"); 

					return new Event(timestamp,realLevel,details);
				}
				
				 
  
				// *********
 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Event();

	}

	private Event createEvent() {		
		return readFile();
	}

	private void sendEvent() {
		Event event = createEvent();
		if(!event.isEmpty()) {
			queue.acceptEvent(event);
			System.out.println("Dodano nowy event:");
			event.showEvent();
		}
	}

	public void run() {
		while (true) {
			try {
				sleep(Application.INPUT_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendEvent();
		}
	}
}
