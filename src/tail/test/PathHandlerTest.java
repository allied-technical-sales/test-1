package tail.test;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import tail.PathHandler;

public class PathHandlerTest {

	private static Path p; 
	private static PathHandler ph;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ph = new PathHandler("testfiles/test.txt");
		p = Paths.get("testfiles/test.txt");
		
	}

	@Test
	public void testGetPath() {
		assertEquals(ph.getPath(), p);

	}

}
