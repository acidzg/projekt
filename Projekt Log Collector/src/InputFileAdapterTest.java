import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.sql.Timestamp;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	
	@Test
	public void testconnectToQueueManager() {

		testqueue = new QueueManager(null);
		testinput = new InputFileAdapter(testguiConsole);
		assertNotEquals(testqueue, testinput);
		
	}
}
