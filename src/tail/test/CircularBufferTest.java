package tail.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tail.CircularBuffer;

public class CircularBufferTest {
	
	private static CircularBuffer cb;
	private static List<String> l;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// size of buffer is only 2
		cb = new CircularBuffer(2);
		l = new ArrayList<String>();
		// add four items
		cb.add("this");
		cb.add("is");
		cb.add("A");
		cb.add("TeSt");
		
		// only two should be in buffer
		l.add("A");
		l.add("TeSt");
		
	}


	@Test
	public void testData() {
		assertEquals(cb.data(),l);
	}

}
