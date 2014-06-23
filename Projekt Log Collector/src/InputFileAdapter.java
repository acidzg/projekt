import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.JTextArea;

@SuppressWarnings("unused")
public class InputFileAdapter extends Thread implements InputAdapter {
	private Configuration configuration;
	private QueueManager queue;
	private BufferedReader bufferedReader;
	JTextArea guiConsole;

	public InputFileAdapter (JTextArea guiConsole)
	{
		this.guiConsole=guiConsole;
	}
	public void setupConfig(Configuration config) {
		this.configuration = config;
	}

	public void connectToQueueManager(QueueManager queue) {
		this.queue = queue;
	}

	private void readFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(
					"server.log.1");
			bufferedReader = new BufferedReader(new InputStreamReader(
					fileInputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sleep(5000);

				// *********

				if (!line.isEmpty()) {

					Timestamp timestamp = getDateFromline(line);
					Level level = getLevelFromline(line);
					String details = getDetailsFromLine(line);

					Event event = new Event(timestamp, level, details);
					this.guiConsole.append(timestamp +" "+ level +" "+ details + "\n");
					sendEvent(event);

				}

				// *********

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private Timestamp getDateFromline(String line) {
		String regularExp = "(\\))(.+)";
		String date = line.replaceAll(regularExp, "$1");

		String regularExp2 = "(\\()(.+)(\\))";

		String date2 = date.replaceAll(regularExp2, "$2");

		String regularExp3 = "(.+)(T)(.+)";

		String yearMonthDay = date2.replaceAll(regularExp3, "$1");
		String hourMinuteSecMil = date2.replaceAll(regularExp3, "$3");

		String regularExp4 = "([0-9]+)(-)([0-9]+)(-)([0-9]+)";

		Integer year = Integer.parseInt(yearMonthDay.replaceAll(regularExp4,
				"$1"));
		Integer month = Integer.parseInt(yearMonthDay.replaceAll(regularExp4,
				"$3"));
		Integer day = Integer.parseInt(yearMonthDay.replaceAll(regularExp4,
				"$5"));

		String regularExp5 = "([0-9]+)(:)([0-9]+)(:)([0-9]+)(\\.)([0-9]+)";

		Integer hour = Integer.parseInt(hourMinuteSecMil.replaceAll(
				regularExp5, "$1"));
		Integer minute = Integer.parseInt(hourMinuteSecMil.replaceAll(
				regularExp5, "$3"));
		Integer sec = Integer.parseInt(hourMinuteSecMil.replaceAll(regularExp5,
				"$5"));
		Integer mil = Integer.parseInt(hourMinuteSecMil.replaceAll(regularExp5,
				"$7"));

		return new Timestamp(year - 1900, month - 1, day, hour, minute, sec,
				mil);
	}

	private Level getLevelFromline(String line) {
		String regularExpLevel = "(.+)(INFO|WARNING|SEVERE|CONFIG|FINE|FINER|FINEST)(.+)";
		String level = line.replaceAll(regularExpLevel, "$2");
		return Level.parse(level);
	}

	/**
	 * wysiaga z parametru line Details logu i zwraca jako string.
	 * @param
	 * @return
	 */ 
	private String getDetailsFromLine(String line) {
		String regularExpDetails = "(.+)(INFO|WARNING|SEVERE|CONFIG|FINE|FINER|FINEST)(.+)";
		return line.replaceAll(regularExpDetails, "$3");
	}



	/**
	 * tworzenie nowego event.
	 * @return
	 */
	private Event createEvent() {

		return new Event();
	}

	/**
	 *	wysylanie eventu do kolejki.
	 * @param pEvent
	 */
	private void sendEvent(Event pEvent) {
		Event event = pEvent;
		if (!event.isEmpty()) {
			queue.acceptEvent(event);
			System.out.println("Dodano nowy event:");
			event.showEvent();
		}
	}

	/**
	 * chyba wszyscy wiedza co to jest;p.
	 */
	public final void run() {
		while (true) {
			try {
				sleep(Application.INPUT_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			readFile();
		}
	}

	/**
	 * zamykanie strumienia.
	 */
	public final void finalize() {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
