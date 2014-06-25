import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class OutputDatabaseAdapterTest {
	
	private Configuration configuration;
	private Event event;
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

	@Test
	public void testOutputDatabaseAdapter() {
		//	fail("Not yet implemented");
			}

	@Test
	public void testSetupConfig() {

		configuration = new Configuration();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		assertNotEquals(configuration, outputDatabaseAdapter);
		
	}

	@Test
	public void testStoreEvents() {
		event = new Event();
		outputDatabaseAdapter = new OutputDatabaseAdapter();
		assertNotEquals(event, outputDatabaseAdapter);
	

	}

}
