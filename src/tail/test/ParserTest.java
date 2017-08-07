package tail.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import tail.Parser;

public class ParserTest {
	static Parser p;
	public static String[] args;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		args = new String[3];
		args[0] = "testfiles/test.txt";
		args[1] = "-n";
		args[2] = "10";
		p = new Parser(args);
		
	}

	@Test
	public void testGetFileName() {
		assertEquals(p.getFileName(), args[0]);
	}

	@Test
	public void testGetCount() {
		assertEquals(p.getCount(), 10);
	}

}
