import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bahyr
 */

public class TestAppCore {

	/**
	 *obiekty z class QueueManager
	 *@see QueueManager
	 */
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

	/** QueueManager Test **/
	@Test
	public void acceptEvent() {
		event = new Event();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		queue = new QueueManager(outputDatabaseAdapter);

		/** test zwraca wartosc poprzez fukccje acceptEvent z klasy QueueManager
		 * @see QueueManager
		 * @param acceptEvent
		 * **/

		assertEquals(true, queue.acceptEvent(event));
	}

	@Test
	public void sendEvent() {
		event = new Event();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		queue = new QueueManager(outputDatabaseAdapter);

		/** test zwraca wartosc przez funkcje sendEvents z klasy QueueManager
		**/
		assertEquals(true, queue.sendEvents());
	}

	/** Test class Event **/
	@Test
	public void isEmpty() {
		event = new Event();

		/** test zwraca watrosc przez funkcje isEmpty z klasy Event
		 * **/
		assertEquals(true, event.isEmpty());

	}

	@Test
	public void getTimestamp() {
		event = new Event();

		/** test not Null zwraca watrosc przez funkcje getTimestamp
		 * **/
		assertNotNull(event.getTimestamp());
	}

	@Test
	public void getLogLevel() {
		event = new Event();

		/** test not Null zwraca watrosc przez funkcje getLogLevel
		 * **/
		assertNotNull(event.getLogLevel());
	}

	@Test
	public void getDetails() {
		event = new Event();
		/** test not Null zwraca watrosc przez funkcje getDetails
		 * **/

		assertNotNull(event.getDetails());
	}

}
