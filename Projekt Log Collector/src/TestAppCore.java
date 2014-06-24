import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAppCore {

	// Objects from class QueueManager
	private Event event;
	private QueueManager queue;
	private OutputDatabaseAdapter outputDatabaseAdapter;


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

	// QueueManager Test
	@Test
	public void acceptEvent() {
		event = new Event();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		queue = new QueueManager(outputDatabaseAdapter);

		// test return value by function acceptEvent from class QueueManager
		assertEquals(true, queue.acceptEvent(event));
	}

	@Test
	public void sendEvent() {
		event = new Event();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		queue = new QueueManager(outputDatabaseAdapter);

		// test return value by function sendEvent from class QueueManager
		assertEquals(true, queue.sendEvents());
	}
	


	
	

}
