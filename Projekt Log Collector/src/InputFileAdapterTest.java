import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *test adaptera
 */

@SuppressWarnings("unused")
public class InputFileAdapterTest {
	private Configuration testconfiguration;
	private QueueManager testqueue;
	private BufferedReader testbufferedReader;
	private InputFileAdapter testinput;
	JTextArea testguiConsole;

	public InputFileAdapterTest (){}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInputFileAdapter() {
		assertNotNull(new InputFileAdapter(testguiConsole));

	}

	@Test
	public void testSetupConfig() {

		testconfiguration = new Configuration();
		testinput = new InputFileAdapter(testguiConsole);
		assertNotEquals(testconfiguration, testinput);

	}

	/**
	 * Test polaczenia z kolejka
	 */
	@Test
	public void testconnectToQueueManager() {

		testqueue = new QueueManager(null);
		testinput = new InputFileAdapter(testguiConsole);
		assertNotEquals(testqueue, testinput);

	}

	/**
	 * Test metody wycinajacej date
	 */
	@Test
	@SuppressWarnings("deprecation")
	public void getDateFromline(){

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(
					"server.log.1");

		testbufferedReader = new BufferedReader(new InputStreamReader(
				fileInputStream));
		String line;
		testinput = new InputFileAdapter(testguiConsole);

			while ((line = testbufferedReader.readLine()) != null) {

				if (!line.isEmpty()) {

			Timestamp Timestamporg = testinput.getDateFromline(line);

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

			Timestamp testTimestamp = new Timestamp(year - 1900, month - 1, day, hour, minute, sec,
					mil);

			assertEquals(testTimestamp,Timestamporg);

				}
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();}
		}

	/**
	 * Test metody wycinajacej detale
	 */
	@Test
	public void TestgetDetailsFromLine(){

		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(
					"server.log.1");

		testbufferedReader = new BufferedReader(new InputStreamReader(
				fileInputStream));
		String line;
		testinput = new InputFileAdapter(testguiConsole);

			while ((line = testbufferedReader.readLine()) != null) {

				if (!line.isEmpty()) {

			String detailorg = testinput.getDetailsFromLine(line);

			String regularExpDetails = "(.+)(INFO|WARNING|SEVERE|CONFIG|FINE|FINER|FINEST)(.+)";
			String detail = line.replaceAll(regularExpDetails, "$3");

			assertEquals(detail,detailorg);

				}
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();}
		}

		public final void finalize() {
			try {
				testbufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


}
