package tail.test;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import tail.ReadData;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReadDataTest {
	
	private static Path p; 
	private static ReadData rd;
	private static List<String> l;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = Paths.get("testfiles/test.txt");
		rd = new ReadData(p, 5);
		l = new ArrayList<String>();
		l.add("10");
		l.add("11");
		l.add("12");
		l.add("13");
		l.add("14");
	}


	@Test
	public void testGetData() {
		assertEquals(rd.getData(), l );
	}

}
