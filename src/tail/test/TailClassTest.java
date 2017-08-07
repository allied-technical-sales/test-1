package tail.test;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tail.TailClass;

public class TailClassTest {
	private static TailClass tc;
	private static List<String> l;
	private static String[] args;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		args = new String[3];
		args[0] = "testfiles/test.txt";
		args[1] = "-n";
		args[2] = "3";
		tc = new TailClass(args);
		l = new ArrayList<String>();
		l.add("12");
		l.add("13");
		l.add("14");
	}

	@Test
	public void testGetData() {
		assertEquals(tc.getData(),l);
	}

}
